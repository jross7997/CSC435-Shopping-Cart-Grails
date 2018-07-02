package shop

class APIinfo {

    String operator
    String URI
    String description
    
    def APIinfo(String o, String u, String d){
        operator = o
        URI = u
        description = d
    }
    
    static constraints = {
    }
}
