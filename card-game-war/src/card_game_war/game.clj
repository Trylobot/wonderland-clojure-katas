(ns card-game-war.game (:gen-class))

;; feel free to use these cards or use your own data structure
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def suits [:spade :club :diamond :heart])
(def cards
  (for [rank ranks
        suit suits]
    [rank suit]))

(defn rank-of [card] (card 0))
(defn suit-of [card] (card 1))

(defn card-value [card]
    (+ (* 10 (.indexOf ranks (rank-of card))) (.indexOf suits (suit-of card))) )

(defn compare-cards [card-p1 card-p2]
    (> (card-value card-p1) (card-value card-p2)))

(defn create-game []
    (let [deck (shuffle cards)]
        [(take-nth 1 deck) (take-nth 1 (rest deck))] ) )

(defn play-round [game]
    (let [deck-p1 (game 0) 
          deck-p2 (game 1)
          card-p1 (peek deck-p1)
          card-p2 (peek deck-p2)
          new-deck-p1 (pop deck-p1)
          new-deck-p2 (pop deck-p2)]
    (if (compare-cards card-p1 card-p2)
        [(into [card-p2 card-p1] new-deck-p1) new-deck-p2]
        [new-deck-p1 (into [card-p1 card-p2] new-deck-p2)]) ))

(defn play-game [game]
    (let [deck-p1 (game 0) 
          deck-p2 (game 1)]
    (cond 
        (= 0 (count deck-p2)) {:winner "Player 1", :cards deck-p1}
        (= 0 (count deck-p1)) {:winner "Player 2", :cards deck-p2}
        :else (recur (play-round game)) )))


