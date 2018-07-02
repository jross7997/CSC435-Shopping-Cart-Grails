package shop

class ShopController {
    static allowedMethods = [index: 'GET', addToCart: 'PUT']
    def index() {
        def o = Food.findAll()  
        response.status = 200
        ViewController view = new ViewController()    
        view.view(o)
    }
    def addToCart() {
        String id = params.id
        int i = Integer.parseInt(id)
        def f = Food.get(i)
        
        if(session.cart != null){
            def cList = session.cart
            if(f != null){
                cList.add(f)
                session.cart = cList
                
                response.status = 201
                ViewController view = new ViewController()    
                view.view(new Status(201,"Object Added"))
            }else{
                response.status = 400
                ViewController view = new ViewController()    
                view.view(new Status(400,"Object was null"))
            }
        }else{
            def list = []
            if(f != null){
                list.add(f)
                session.cart = list
                response.status = 201
                ViewController view = new ViewController()    
                view.view(new Status(201,"Object Added"))
            }else{
                response.status = 400
                ViewController view = new ViewController()    
                view.view(new Status(400,"Object was null"))
            }
        }
    }
}
