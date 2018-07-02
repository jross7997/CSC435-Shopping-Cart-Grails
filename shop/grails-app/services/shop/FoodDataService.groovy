package shop

import grails.gorm.transactions.Transactional
import grails.transaction.Transactional
import org.grails.web.json.JSONObject

import org.grails.web.json.JSONArray
import groovy.json.JsonSlurper 

@Transactional
class FoodDataService {
    
    def readNutritionAPI(){
        int min = 0
        int max = 2500
        String myURL = "https://api.nutritionix.com/v1_1/search/?results=0%3A20&cal_min="+min+"&cal_max="+max+"&fields=item_name%2Cnf_total_fat%2Cnf_calories%2Cnf_sodium&appId=88cf0044&appKey=90cbe0b7c7beeb26b938584e189be6fd"  
    
        URL url = null
        HttpURLConnection connection = null
        JSONArray arr = null;
        String resp = null;
        try{
            url = new URL(myURL)
            connection = (HttpURLConnection) url.openConnection()
            connection.setRequestMethod("GET")
            connection.setRequestProperty("Content-Type", "text/plain")

            connection.setUseCaches(false)
            connection.setDoInput(true)
            connection.setDoOutput(true)
            
            BufferedReader ins = new BufferedReader(new InputStreamReader(connection.getInputStream()))
            
            String inputLine;
            resp = ""
            while ((inputLine = ins.readLine()) != null) {
                resp = resp + inputLine + "\n";
            }
            ins.close();
            if(resp.equals("")) {
                response.status = 400
                ViewController view = new ViewController()    
                view.view(new Status(400,"Couldn't get data from external API"))
                return
            }
            
            def jsonSlurper = new JsonSlurper()
            Map map = jsonSlurper.parseText(resp)
            def hits = map.get("hits")
            for(rec in hits){
                def foo = rec.fields
                String n = foo.item_name
                String c = foo.nf_calories
                String ft = foo.nf_total_fat
                String s = foo.nf_sodium
                def f = new Food(n,c,ft,s)
                f.save()
            }
            
        }catch(Error e){
            e.printStackTrace()
        }finally{
            connection.disconnect()
        }
     
        if(resp != null){
            //response.status =201
            ViewController view = new ViewController()    
            view.view(new Status(201,"20 Items were added"))
        }
     

    }
}
