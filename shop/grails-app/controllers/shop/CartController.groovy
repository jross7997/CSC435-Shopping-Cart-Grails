package shop

class CartController {
    static allowedMethods = [index: 'GET', addToCart: 'PUT', removeItem: 'DELETE', leave: 'DELETE']
    def index(){
        
        if(session.cart != null){
            
            def list = session.cart  
            double total = 0
            for(item in list){
                if(item != null){
                    total = total + item.price
                }
            }
            def tot = new TotalPrice(total)
                
            def o = [list,tot]
            response.status = 200
            ViewController view = new ViewController()    
            view.view(o)
                
        }else{
            response.status =404
            ViewController view = new ViewController()    
            view.view(new Status(404, "Empty Cart"))
        }
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
    
    def removeItem(){
        String id = params.id
        int i = Integer.parseInt(id)

        if(session.cart != null){
            def cList = session.cart
            def f = null;
            def bool = false
            for(item in cList){
                if(item.id == i && !bool){
                    f = item
                    bool = true
                }
            }
        
            session.cart.remove(f)
            response.status = 204
            ViewController view = new ViewController()    
            view.view(new Status(204,"Item was removed"))
        }else{
            response.status = 400
            ViewController view = new ViewController()    
            view.view(new Status(400,"Cart was null"))
        }
    }    

    
    def leave() {
        session.invalidate()
        response.status = 204
        forward controller: "rest", action: "index"
    }
    
}