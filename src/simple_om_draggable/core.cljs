(ns simple-om-draggable.core
  (:require [cljs.core.async :as async :refer [put! <! mult untap tap chan]]
            [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [goog.events :as events]
            [clojure.set :as set])
  (:require-macros [cljs.core.async.macros :as am :refer [go-loop]]))

;;; Global event channels

(defn- listen [el & types]
  (let [c (chan)]
    (dorun (for [type types]
      (events/listen el type #(put! c %))))
    c))

(def global-mouse-chan
  (mult (listen js/document "mouseup" "mousemove")))

;;; Handlers

(defn- handle-start [e owner]
  ;; save the offset in local state
  (let [client-offset {:left (.-clientX e)
                       :top (.-clientY e)}
        elem-pos (om/get-state owner :position)
        offset (merge-with - client-offset elem-pos)]
    (om/set-state! owner :offset offset))
  ;; start listening to global mouse events
  (tap global-mouse-chan (om/get-state owner :mouse-chan)))

(defn- handle-stop [e owner cursor pos-keys pos-key-map]
  ;; transact the cursor state
  (om/update!
    cursor
    pos-keys
    (set/rename-keys
     (om/get-state owner :position)
     pos-key-map))
  ;; stop listening to global mouse events
  (untap global-mouse-chan (om/get-state owner :mouse-chan))
  ;; reset local dragging state
  (om/set-state! owner :dragging false))

(defn- handle-movement [e owner]
  (let [pos {:top (.-clientY e)
             :left (.-clientX e)}
        offset (om/get-state owner :offset)
        new-pos (merge-with - pos offset)]
    (om/set-state! owner :position new-pos))
  ;; we have to set :dragging to true on move, because you should be
  ;; able to mousedown without meaning to drag (e.g. clicking on links)
  (om/set-state! owner :dragging true))

;;; Wrapper view

(defn draggable-item
  ([view pos-keys]
   (draggable-item view pos-keys {:left :x, :top :y}))
  ([view pos-keys pos-key-map]
   (fn [cursor dragger]
     (reify
       om/IInitState
       (init-state [_]
         {:position
          (set/rename-keys
           (get-in cursor pos-keys)
           (set/map-invert pos-key-map))
          :offset {:left 0 :top 0}
          :dragging false
          :mouse-chan (chan)})

       om/IWillMount
       (will-mount [_]
         (let [mouse-chan (om/get-state dragger :mouse-chan)]
           (go-loop []
             (let [e (<! mouse-chan)]
               (condp = (.-type e)
                 "mousedown" (handle-start e dragger)
                 "mouseup"   (handle-stop e dragger cursor pos-keys pos-key-map)
                 "mousemove" (handle-movement e dragger))
               (recur)))))

       om/IRenderState
       (render-state [_ state]
         (dom/div
          {:style
           {:position "absolute"
            :top (get-in state [:position :top])
            :left (get-in state [:position :left])
            :user-select "none" :-webkit-user-select "none" :-moz-user-select "none" }
           :on-mouse-down #(put! (:mouse-chan state) (.-nativeEvent %))}

          ;; invisible overlay div to block e.g. clicks on links
          (dom/div {:style {:position "absolute"
                            :top 0 :left 0 :right 0 :bottom 0
                            :z-index 2147483647
                            :display (if (:dragging state) "block" "none")}})
          
          (om/build view cursor)))))))
