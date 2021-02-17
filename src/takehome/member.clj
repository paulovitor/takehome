(ns takehome.member
    (:require [java-time :as time]
      [takehome.object :as o]))

(defn is-patriota? [object purchase]
      (and (= (:type purchase) :patriota) (o/patriota-can-access? object)))

(defn is-premium? [object purchase]
      (and (= (:type purchase) :premium) (o/premium-can-access? object)))

(defn is-active? [object purchase]
      (time/before? (:subscription-start purchase)
                    (:released-at object)
                    (:subscription-end purchase)))