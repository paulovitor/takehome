(ns takehome.core
    (:require [java-time :as time]
      [takehome.member :as member]))

; if (object.type == series)
; if (purchase.type == patriota && (object.type in (podcast, debate, interview) && purchase is active)
; if (purchase.type == premium && (object.type in (podcast, debate, interview, course) && purchase is active)
; if (purchase.type == mecenas && (object.type in (podcast, debate, interview, course, patron) && purchase is active)
(defn can-access? [object purchase]
      (cond
        (and (= (:type object) :series) (and (= (:type purchase) :patriota) (member/is-active? (:released-at object) purchase))) true
        (and (= (:type object) :course) (and (= (:type purchase) :premium) (member/is-active? (:released-at object) purchase))) true
        :else false))
