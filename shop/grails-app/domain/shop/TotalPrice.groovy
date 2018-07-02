package shop

import grails.persistence.*

@Entity
class TotalPrice {

    double total
    
    def TotalPrice(double t){
        total = t
    }
    
    static constraints = {
    }
}
