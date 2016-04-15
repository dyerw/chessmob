(ns chessmob.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
  :selected-tile
  (fn [db]
    (reaction (:selected-tile @db))))

(re-frame/register-sub
  :board
  (fn [db]
    (reaction (:board @db))))

