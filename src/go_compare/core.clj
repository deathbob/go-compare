(ns go-compare.core
  (require [clojure.core.async :as async :refer :all])
  (:gen-class :main true)
  )

(def ans (atom []))

(defn baz[]
  (let [c (chan)
        n 100]
    (doseq [i (range n)]
      (go
       (swap! ans conj (slurp "http://golang.org/"))
       (>! c i)))
    (doseq [i (range n)]
      (println (<!! c)))))

(defn -main
  []
  (time (baz))
  (prn (count @ans))
  )
