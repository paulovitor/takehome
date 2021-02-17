(ns takehome.member
    (:require [java-time :as time]))

(defn is-active? [date purchase]
      (time/before? (:subscription-start purchase)
                    date
                    (:subscription-end purchase)))