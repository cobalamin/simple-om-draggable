(ns example.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom]
            [simple-om-draggable.core :refer [draggable-item]]))

(defonce app-state
  (atom {:draggables
         [{:html "<h1>Heading</h1><p><a href=\"http://clojure.org\">Clojure!</a>"
           :position {:x 200 :y 50}}
          {:html "<img src=\"data:image/gif;base64,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\">"
           :position {:x 400 :y 200}}
          {:html "<p>Stubn unbandig des muas ma hoid kenna wuid Schdarmbeaga See unbandig. Kimmt da i hab an griaß God beinand vo de! Da Habedehre af dahoam, glacht des is a gmahde Wiesn Steckerleis. Des muas ma hoid kenna boarischer Almrausch, hi in da! Bittschön is gscheckate Watschnpladdla obacht Klampfn fias mei, Schdarmbeaga See ned woar. De Sonn Wurschtsolod Gschicht schüds nei fensdaln hi aasgem:</p>
<p>Marterl singd und sei, Prosd Woibbadinga Schdarmbeaga See. Allerweil a fescha Bua trihöleridi dijidiholleri wos midanand, nackata? Wuid Guglhupf Fingahaggln woaß, vo de blärrd nomoi iabaroi measi Maibam. A ganze Hoiwe Xaver do wolpern, schoo Greichats! Xaver umma des is schee sodala hogg di hera mogsd a Bussal gar nia need, Ledahosn naa. Gfreit mi heitzdog und glei wirds no fui lustiga?</p>"
           :position {:x 50 :y 300}}]}))

(defn example-view [cursor _]
  (reify
    om/IRender
    (render [_]
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
