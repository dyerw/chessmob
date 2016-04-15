(ns chessmob.views
    (:require [re-frame.core :as re-frame]))

(defn tile-view [color tile]
  [:div {:style {:backgroundColor color :display "inline-block" 
                 :height "40px" :width "40px"}}
        (str (:type tile))])

(defn row-view [first-color row]
  [:div {:style {:height "40px"}} (map tile-view 
                                       (cycle (if (= first-color "white")
                                                  ["white" "black"]
                                                  ["black" "white"])) 
                                       row)])

(defn main-panel []
  (let [board (re-frame/subscribe [:board])]
    (fn []
      [:div (map row-view (cycle ["white" "black"]) @board)])))
