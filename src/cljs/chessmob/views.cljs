(ns chessmob.views
    (:require [re-frame.core :as re-frame]))

(def tile-size 60)
(def tile-size-px (str tile-size "px"))

(defn tile-view [color row-index col-index tile]
  [:div {:on-click #(println row-index col-index)
         :key   col-index
         :style {:backgroundColor color :display "inline-block" 
                 :color (if (= color "white") "black" "white")
                 :height tile-size-px :width tile-size-px}}
        (str (:type tile))])

(defn row-view [first-color row-index row]
  [:div {:key   row-index
         :style {:height tile-size-px}} 
        (map tile-view 
             (cycle (if (= first-color "white")
                      ["white" "black"]
                      ["black" "white"])) 
             (repeat row-index)
             (range (count row))
             row)])

(defn main-panel []
  (let [board (re-frame/subscribe [:board])]
    (fn []
      [:div (map row-view 
                 (cycle ["white" "black"]) 
                 (range (count @board)) 
                 @board)])))

