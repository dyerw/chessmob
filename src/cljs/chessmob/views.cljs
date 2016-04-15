(ns chessmob.views
    (:require [re-frame.core :as re-frame]))

(def tile-size 60)
(def tile-size-px (str tile-size "px"))

(defn tile-view [selected-tile color row-index col-index tile]
  (let [is-selected-tile  (and (not (nil? selected-tile)) 
                               (= (:x selected-tile) row-index)
                               (= (:y selected-tile) col-index))]
    [:div {:on-click #(re-frame/dispatch [:tile-clicked {:x row-index :y col-index}])
           :key   col-index
           :style (merge {:backgroundColor color :display "inline-block" 
                          :color (if (= color "white") "black" "white")
                          :height tile-size-px :width tile-size-px}
                         ;; Check if this is the selected tile and give it a 
                         ;; border if it is
                         (if is-selected-tile 
                             {:border "5px solid green"}
                             {}))}
          (str (:type tile))]))

(defn row-view [selected-tile first-color row-index row]
  [:div {:key   row-index
         :style {:height tile-size-px}} 
        (map tile-view
             (repeat selected-tile) 
             (cycle (if (= first-color "white")
                      ["white" "black"]
                      ["black" "white"])) 
             (repeat row-index)
             (range (count row))
             row)])

(defn main-panel []
  (let [board (re-frame/subscribe [:board])
        selected-tile (re-frame/subscribe [:selected-tile])]
    (fn []
      [:div (map row-view
                 (repeat @selected-tile) 
                 (cycle ["white" "black"]) 
                 (range (count @board)) 
                 @board)])))

