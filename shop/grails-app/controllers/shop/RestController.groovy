package shop

//import org.json.JSON


class RestController {

static allowedMethods = [index: 'GET']    
    
    def index() { 
        
        def arr1 = [new APIinfo("GET","/rest/index" ,"Get the functionality of the API")]
       
        def arr2 = [new APIinfo("GET", "/data/index", "Shows the entire database"),
                    new APIinfo("GET","/data/getOne?id=?","Get one item by its ID"),
                    new APIinfo("POST", "/data/postOne?name=?&calories=?&fat=?&sodium=?", "Add a new item to the database"),
                    new APIinfo("POST", "/data/post20","Get 20 items from an external API"),
//                    //Remove?
//                    new APIinfo("PUT", "/data/update?name=?&calories=?&fat=?&sodium=?&id=?", "Update the information of an existing item"),
                    //Remove?
//                    new APIinfo("DELETE","data/deleteOne?id=?","Delete one item from database"),
                    new APIinfo("DELETE", "/data/deleteAll", "Delete entire database")]
                
        def arr3 = [new APIinfo("GET","/shop/index","Retrieves the current database"),
                    new APIinfo("PUT","/shop/addToCart?id=?", "Add an item to your cart")]
                
        def arr4 = [new APIinfo("GET","/cart/index","Retrieves cart and total price of all items"),
                    new APIinfo("PUT","/cart/addToCart?id=?","Add an item to your cart"),
                    new APIinfo("DELETE","/cart/removeFromCart?id=?","Remove an item from your cart")]
        
        def map = [arr1,arr2,arr3,arr4]
        
        response.status = 200
        ViewController view = new ViewController()
        view.view(map)
}

}
