(ns takehome.core-test
    (:require [clojure.test :refer :all]
      [java-time :as time]
      [takehome.core :as sub]))

(deftest test-patriota
         (are [result purchase] (= result
                                   (sub/can-access?
                                     {:type :series :name "1964: O Brasil entre Armas e Livros", :released-at (time/local-date-time "2019-07-24T20:02:34.691")}
                                     purchase))
              true {:type :patriota
                    :subscription-start (time/local-date-time "2019-01-24T11:46:22.811")
                    :subscription-end (time/local-date-time "2020-01-24T11:46:22.811")}
              false {:type :patriota
                     :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
                     :subscription-end (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-premium
         (are [result purchase] (= result
                                   (sub/can-access?
                                     {:type :course :name "Fé e Liberdade", :released-at (time/local-date-time "2020-02-06T22:58:52.080")}
                                     purchase))
              true {:type :premium
                    :subscription-start (time/local-date-time "2020-01-24T11:46:22.811")
                    :subscription-end (time/local-date-time "2021-01-24T11:46:22.811")}
              false {:type :premium
                     :subscription-start (time/local-date-time "2020-01-24T11:46:22.811")
                     :subscription-end (time/local-date-time "2020-01-24T11:46:22.811")}))
