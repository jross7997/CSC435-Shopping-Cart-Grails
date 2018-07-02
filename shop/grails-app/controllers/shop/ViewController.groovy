package shop

import grails.converters.JSON

class ViewController {
    
    def ViewController(){
        
    }

    def view(Object o) { 
       response.contentType = "application/json"
       render o as JSON
    }
}
