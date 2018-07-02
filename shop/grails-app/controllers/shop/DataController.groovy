package shop

class DataController {

    static allowedMethods = [index: 'GET', getOne: 'GET', update: 'PUT', post20: 'POST', postOne: 'POST', deleteAll: 'DELETE', deleteOne: 'DELETE']   
    
    def index() {        
        def o = Food.findAll()  
        response.status = 200
        ViewController view = new ViewController()    
        view.view(o)
    }
    
    def getOne() {
        String id = params.id
        int i = Integer.parseInt(id)
        def f = Food.get(i)
        
        response.status = 200
        ViewController view = new ViewController()    
        view.view(f)
    }
    
    def deleteAll() {
        Food.executeUpdate('delete from Food')
        response.status = 204
        ViewController view = new ViewController()    
        view.view(new Status(204,"All Data was deleted"))
    }
    //fix
//    def deleteOne() {
//        String id = params.id
//        int i = Integer.parseInt(id)
//        def f = Food.get(i)
//        f.delete()
//        
//        response.status = 204
//        ViewController view = new ViewController()    
//        view.view(new Status(204,"One Item was Deleted"))
//    }
    
    def postOne() {
        String name = params.name
        String cal = params.calories
        String fatty = params.fat
        String sodium = params.sodium
        
        if(name.contains("-")){
            name = name.replace("'"," ")
        }
        
        def f = new Food(name,cal,fatty,sodium)
        f.save()
        
        response.status = 201
        ViewController view = new ViewController()    
        view.view(new Status(201,"One Item was added"))
    }  
    //Fix later
//  def update(){
//      String name = params.name
//      String cal = params.calories
//      String fatty = params.fat
//      String sodium = params.sodium
//      String id = params.id
//      
//      int i = Integer.parseInt(id)
//      
//      def f = Food.get(i)
//      f.name = name
//      f.calories = cal
//      f.fat = fatty
//      f.sodium = sodium
//      f.save()
//      
//      response.status = 201
//      ViewController view = new ViewController()    
//      view.view(new Status(201, "item was updated"))  
//  }  
    
  def post20(){
      def helper = new FoodDataService();
      helper.readNutritionAPI();
    }
}
