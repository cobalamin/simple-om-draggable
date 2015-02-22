(ns example.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom]
            [simple-om-draggable.core :refer [draggable-item]]))

(defonce app-state
  (atom {:draggables
         [{:html "<h1>Heading</h1><p><a href=\"http://clojure.org\">Clojure!</a>"
           :position {:x 200 :y 50}}
          {:html "<img src=\"http://www.nasa-usa.de/sites/default/files/styles/430x323/public/larsoncollinscomposite_jla_2.jpg\">"
           :position {:x 400 :y 200}}
          {:html "<p>Stubn unbandig des muas ma hoid kenna wuid Schdarmbeaga See unbandig. Kimmt da i hab an griaß God beinand vo de! Da Habedehre af dahoam, glacht des is a gmahde Wiesn Steckerleis. Des muas ma hoid kenna boarischer Almrausch, hi in da! Bittschön is gscheckate Watschnpladdla obacht Klampfn fias mei, Schdarmbeaga See ned woar. De Sonn Wurschtsolod Gschicht schüds nei fensdaln hi aasgem:</p>
<p>Marterl singd und sei, Prosd Woibbadinga Schdarmbeaga See. Allerweil a fescha Bua trihöleridi dijidiholleri wos midanand, nackata? Wuid Guglhupf Fingahaggln woaß, vo de blärrd nomoi iabaroi measi Maibam. A ganze Hoiwe Xaver do wolpern, schoo Greichats! Xaver umma des is schee sodala hogg di hera mogsd a Bussal gar nia need, Ledahosn naa. Gfreit mi heitzdog und glei wirds no fui lustiga?</p>"
           :position {:x 50 :y 300}}]}))

(defn example-view [cursor _]
  (reify
    om/IRender
    (render [_]
      (.log js/console "example-view")
      (dom/div
       #js {:dangerouslySetInnerHTML #js {:__html (:html cursor)}
            :style #js {:max-width "500px"
                        :box-shadow "0 0 5px #333"
                        :padding "15px"
                        :background-color "#fff"}}))))

(def draggable-example-view
  (draggable-item example-view [:position]))

(defn main-view [cursor _]
  (reify
    om/IRender
    (render [_]
      (apply dom/div nil
       (om/build-all draggable-example-view (:draggables cursor))))))

(om/root
 main-view
 app-state
 {:target (.getElementById js/document "app")})
