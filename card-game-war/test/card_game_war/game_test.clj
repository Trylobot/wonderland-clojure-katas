(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= 
      ; given input + actual output
      (play-round
        [ [[2 :spade] [:ace :heart]]
          [[2 :club] [:ace :diamond]] ] ) 
      ; expected output
      [ [[:ace :diamond] [:ace :heart] [2 :spade]]
        [[2 :club]] ] )) )
  (testing "queens are higher rank than jacks")
  (testing "kings are higher rank than queens")
  (testing "aces are higher rank than kings")
  (testing "if the ranks are equal, clubs beat spades")
  (testing "if the ranks are equal, diamonds beat clubs")
  (testing "if the ranks are equal, hearts beat diamonds") )

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= 
      ; given input + actual output
      (play-game
        [ [[2 :spade] [:ace :heart]]
          [[2 :club] [:ace :diamond]] ] ) 
      ; expected output
      {:winner "Player 1",
       :cards [[2 :spade] [:ace :diamond] [2 :club] [:ace :heart]]} ))) )

