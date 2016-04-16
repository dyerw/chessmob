(ns chessmob.handlers
    (:require [re-frame.core :as re-frame]
              [chessmob.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(defn swap-tiles [board tile1-coord tile2-coord]
  (let [tile1          (nth (nth board (:x tile1-coord)) (:y tile1-coord))
        tile2          (nth (nth board (:x tile2-coord)) (:y tile2-coord))
        ;; The board with tile2 moved to tile1
        one-move-board (assoc board 
                              (:x tile1-coord)
                              (assoc (nth board (:x tile1-coord)) 
                                     (:y tile1-coord) 
                                     tile2))]
    (assoc one-move-board 
           (:x tile2-coord)
           (assoc (nth one-move-board (:x tile2-coord)) 
                  (:y tile2-coord) 
                  tile1)))) 

(re-frame/register-handler
 :tile-clicked
 (fn [db [_ coordinates]]
   (let [selected-tile (:selected-tile db)]
     (if (nil? selected-tile)
         (if (= (:type (nth (nth (:board db) (:x coordinates)) (:y coordinates))) 'empty-space)
             db
             (assoc db :selected-tile coordinates))
         (assoc (assoc db :selected-tile nil) 
                :board 
                (swap-tiles (:board db) (:selected-tile db) coordinates))))))
