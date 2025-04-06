package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Notification;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.notification.NotificationService;
import site.easy.to.build.crm.service.ticket.TicketService;
import site.easy.to.build.crm.util.AuthenticationUtils;

import java.util.List;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final AuthenticationUtils authenticationUtils;
    private final DepenseService depenseService;
    private final LeadService leadService;
    private final TicketService ticketService;


    @Autowired
    public NotificationController(NotificationService notificationService, AuthenticationUtils authenticationUtils,
     DepenseService depenseService, LeadService leadService, TicketService ticketService) {
        this.notificationService = notificationService;
        this.authenticationUtils = authenticationUtils;
        this.depenseService = depenseService;
        this.leadService = leadService;
        this.ticketService = ticketService;
    }



    // 🔹 Récupérer toutes les notifications de l'utilisateur connecté, triées par date (du plus récent au plus ancien)
    @GetMapping("/notif")
    public String getNotificationsByUser(Authentication authentication, Model model) {
        // Récupérer l'ID de l'utilisateur connecté
        int userId = authenticationUtils.getLoggedInUserId(authentication);

        // Récupérer les notifications de cet utilisateur
        List<Notification> notifications = notificationService.getRecentNotifications(userId);
        model.addAttribute("notifications", notifications);

        return "notification/notificationView"; // Vue Thymeleaf pour afficher la liste des notifications
    }

    // 🔹 Mettre à jour l'état d'une notification
    @PostMapping("/{notificationId}/etat/{newEtat}")
    public String updateNotificationEtat(@PathVariable int notificationId,
     @PathVariable int newEtat, Authentication authentication) {
        // Récupérer l'ID de l'utilisateur connecté (pour validation)
        int userId = authenticationUtils.getLoggedInUserId(authentication);

        // Mettre à jour l'état de la notification
        notificationService.updateNotificationEtat(notificationId, newEtat);
        Notification notification = notificationService.findByNotificationId(notificationId);
        Depense depense = depenseService.getDepenseById(notification.getIdDepense()).get();
        
        if(depense!= null){
            depense.setEtat(newEtat);
            depenseService.saveDepense(depense);
            if (newEtat==0) {
                notificationService.delete(notification);
                depenseService.deleteDepense(depense.getDepenseId());
                Lead lead=depense.getLead();
                if (lead!=null) {
                    
                    System.out.println(lead.getLeadId());
                    leadService.delete(lead);
                }
                else{
                    
                    System.out.println(depense.getTicket().getTicketId());
                    ticketService.delete(depense.getTicket());
                }
            }    
        }
        
        

        return "redirect:/notifications/notif"; // Rediriger vers la liste des notifications
    }
}
