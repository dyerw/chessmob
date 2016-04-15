(ns chessmob.db)

(def default-board
  [[{:type 'rook :color 'black} {:type 'knight :color 'black}
    {:type 'bishop :color 'black} {:type 'queen :color 'black}
    {:type 'king :color 'black} {:type 'bishop :color 'black}
    {:type 'knight :color 'black} {:type 'rook :color 'black}]
   (into [] (repeat 8 {:type 'pawn :color 'black}))
   (into [] (repeat 8 {:type 'empty-space}))
   (into [] (repeat 8 {:type 'empty-space}))
   (into [] (repeat 8 {:type 'empty-space}))
   (into [] (repeat 8 {:type 'empty-space}))
   (into [] (repeat 8 {:type 'pawn :color 'white}))
   [{:type 'rook :color 'white} {:type 'knight :color 'white}
    {:type 'bishop :color 'white} {:type 'king :color 'white}
    {:type 'queen :color 'white} {:type 'bishop :color 'white}
    {:type 'knight :color 'white} {:type 'rook :color 'white}]])

(def default-db
  {:board default-board
   :selected-tile nil})

