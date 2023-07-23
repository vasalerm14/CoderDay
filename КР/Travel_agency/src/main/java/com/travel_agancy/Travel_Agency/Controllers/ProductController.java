package com.travel_agancy.Travel_Agency.Controllers;
import com.travel_agancy.Travel_Agency.models.client;
import com.travel_agancy.Travel_Agency.models.voucher;
import com.travel_agancy.Travel_Agency.services.voucher_services;
import com.travel_agancy.Travel_Agency.services.client_services;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class ProductController {
    private String Phone = "";
    private final voucher_services vs = new voucher_services();
    private final client_services cs = new client_services();
    @GetMapping("/")
    public String products(Model model) {
        List<voucher> voucher = vs.getAllvouch();
        model.addAttribute("vouchers",voucher);
        return "products.html";
    }

    @GetMapping("/Client")
    public String client(Model model) {
        List<voucher> voucher = vs.getAllvouch();
        model.addAttribute("vouchers",voucher);
        return "Client.html";
    }

    @GetMapping("/Administrator")
    public String Administrator(Model model) {
        List<voucher> voucher = vs.getAllvouch();
        List<client> clients_all = cs.getAllclient();
        model.addAttribute("vouchers",voucher);
        model.addAttribute("clients_all",clients_all);
        model.addAttribute("clients",cs.get_client_info(Phone));
        model.addAttribute("total_value",cs.total_revenue());
        return "Administrator.html";
    }

    @PostMapping("/delete")
    public String delete_from_db(@RequestParam(name = "id") int id_del){
        vs.remove_vouch(id_del);

        return "redirect:/Administrator";
    }

    @PostMapping("/add")
    public String add_vouch(@RequestParam(name="id") int id_add,@RequestParam(name="name_vouch") String vouch_name
    ,@RequestParam(name="price") int price,@RequestParam(name="col_day") int col_day){
        vs.add_vouch(id_add,vouch_name,price,col_day);
        return "redirect:/Administrator";
    }

    @PostMapping("/add_vouch_client")
    public String test(@RequestParam(name="id") int id,@RequestParam(name="First_name") String First_name,
    @RequestParam(name="Surname") String Surname,@RequestParam(name="Phone") String Phone){
        cs.add_client_vouch(id,First_name,Surname,Phone);
        return "redirect:/Client";
    }

    @PostMapping("/get_client_info")
    public String client_info(Model model,@RequestParam(name="Phone") String phone){
        Phone = phone;
        cs.get_client_info(Phone);
        return "redirect:/Administrator";
    }

    @PostMapping("/del_client")
    public String delete_client(@RequestParam(name = "First_name") String First_name,
                                 @RequestParam(name="Surname") String Surname,
                                @RequestParam(name="Phone") String Phone){
        cs.del_client(First_name,Surname,Phone);

        return "redirect:/Administrator";
    }


}
