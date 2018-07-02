package shop

import grails.persistence.*

@Entity
class Food {

    String name
    String calories
    String fat
    String sodium
    double price

    int id
    
    def Food(String n, String c, String f, String s){
        if(n != null){
            name = n
        }
        if(c != null){
            calories = c
        }else{
            calories = "0"
        }
        if(f != null){
            fat = f
        }else{
            fat = "0"
        }
        if(s != null){
            sodium = s
        }else{
            sodium = "0"
        }
        
        price = ((Double.parseDouble(calories) + Double.parseDouble(fat) + Double.parseDouble(sodium))/100)
    }
    
    static constraints = {
    }
}
