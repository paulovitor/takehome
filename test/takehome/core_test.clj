(ns takehome.core-test
    (:require [clojure.test :refer :all]
      [java-time :as time]
      [takehome.core :as sub]))

(deftest test-series
         (is (= true (sub/can-access?
                       {:type :series :name "Pátria Educadora", :released-at (time/local-date-time "2020-03-31T02:10:38.606")}
                       {}))))

(deftest test-patriota-can-access-in-subscription
         (are [result purchase] (= result (sub/can-access?
                                     {:type :interview :name "1964: O Brasil entre Armas e Livros", :released-at (time/local-date-time "2019-07-24T20:02:34.691")}
                                     purchase))
              true {:type :patriota
                    :subscription-start (time/local-date-time "2019-01-24T11:46:22.811")
                    :subscription-end (time/local-date-time "2020-01-24T11:46:22.811")}
              false {:type :patriota
                     :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
                     :subscription-end (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-patriota-can-not-access
         (is (= false (sub/can-access?
                  {:type :course :name "Arte, Imaginação e Sentido", :released-at (time/local-date-time "2019-06-14T18:15:50.853")}
                  {:type :patriota :subscription-start (time/local-date-time "2019-01-24T11:46:22.811") :subscription-end (time/local-date-time "2020-01-24T11:46:22.811")}))))

(deftest test-premium-can-access-in-subscription
         (are [result purchase] (= result (sub/can-access?
                                     {:type :course :name "Fé e Liberdade", :released-at (time/local-date-time "2020-02-06T22:58:52.080")}
                                     purchase))
              true {:type :premium
                    :subscription-start (time/local-date-time "2020-01-24T11:46:22.811")
                    :subscription-end (time/local-date-time "2021-01-24T11:46:22.811")}
              false {:type :premium
                     :subscription-start (time/local-date-time "2020-01-24T11:46:22.811")
                     :subscription-end (time/local-date-time "2020-01-24T11:46:22.811")}))

(deftest test-premium-can-not-access
         (is (= false (sub/can-access?
                        {:type :patron :name "Relatório Mecenas", :released-at (time/local-date-time "2020-08-10T20:00:00.656")}
                        {:type :premium :subscription-start (time/local-date-time "2020-01-24T11:46:22.811") :subscription-end (time/local-date-time "2021-01-24T11:46:22.811")}))))
