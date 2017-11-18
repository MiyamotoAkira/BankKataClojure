(ns bank-kata-clj.core)

(defn deposit
  [account amount date]
  (conj account {:amount amount :date date}))

(defn transactions [account]
  account)
