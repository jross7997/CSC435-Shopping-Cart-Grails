package shop

import grails.persistence.*

@Entity
class Status {
    
    int code
    String description
    
    def Status(int i, String s){
        code = i
        description = s
    }

    static constraints = {
    }
}
