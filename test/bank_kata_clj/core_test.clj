(ns bank-kata-clj.core-test
  (:require [clojure.test :refer :all]
            [bank-kata-clj.core :refer :all]
            [clj-time.local :as l]))


(def amount 100)
(def amount2 150)
(def currenttime (l/local-now))

(defn testsingle [newaccount amount currenttime]
  (is (not (nil? (some #(= (:amount %) amount) (transactions newaccount)))))
  (is (not (nil? (some #(= (:date %) currenttime) (transactions newaccount))))))

(defn testmultiple [account teststodo]
  (map #(testsingle account (first %) (second %)) teststodo))

(deftest deposits
  (testing "deposit a quantity on an account"
    (-> []
        (deposit amount currenttime)
        (testsingle amount currenttime)))
  
  (testing "adding two deposits on an account"
    (-> []
        (deposit amount currenttime)
        (deposit amount2 currenttime)
        (testmultiple  [[amount currenttime] [amount2 currenttime]])
        (doall))
    ))
