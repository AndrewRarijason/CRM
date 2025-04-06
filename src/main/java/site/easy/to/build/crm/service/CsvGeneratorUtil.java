package site.easy.to.build.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;

@Component
public class CsvGeneratorUtil {

    private static final String CSV_HEADER = "Customer_name, phone, address, city, state, country, user_id, description, position, twitter, facebook, youtube, created_at, email\n";
    private static final String CSV_HEADER_TICKETS = "Ticket_subject, description, status, priority, created_at\n";
    private static final String CSV_HEADER_LEADS = "Lead_name, phone, status, meeting_id, google_drive, google_drive_folder_id, created_at\n";

    @Autowired
    private TicketService ticketService;

    @Autowired
    private LeadService leadService;

    public String generateCustomerCsv(Customer customer1) {
        StringBuilder csvContent = new StringBuilder();

        csvContent.append(CSV_HEADER);
    //    csvContent.append(customer1.getCustomerId()).append(",");
        csvContent.append(customer1.getName()).append(" copy").append(",");
        csvContent.append(customer1.getPhone()).append(",");
        csvContent.append(customer1.getAddress()).append(",");
        csvContent.append(customer1.getCity()).append(",");
        csvContent.append(customer1.getState()).append(",");
        csvContent.append(customer1.getCountry()).append(",");
        csvContent.append(customer1.getUser().getId()).append(",");
        csvContent.append(customer1.getDescription()).append(",");
        csvContent.append(customer1.getPosition()).append(",");
        csvContent.append(customer1.getTwitter()).append(",");
        csvContent.append(customer1.getFacebook()).append(",");
        csvContent.append(customer1.getYoutube()).append(",");
        csvContent.append(customer1.getCreatedAt()).append(",");
        csvContent.append("copy_").append(customer1.getEmail()).append("\n");


    //    csvContent.append("\nTickets\n");
        csvContent.append("\n").append(CSV_HEADER_TICKETS);

        List<Ticket> tickets = ticketService.findCustomerTickets(customer1.getCustomerId());
        for (Ticket ticket : tickets) {
        //    csvContent.append(ticket.getTicketId()).append(",");
            csvContent.append(ticket.getSubject()).append(",");
            csvContent.append(ticket.getDescription()).append(",");
            csvContent.append(ticket.getStatus()).append(",");
            csvContent.append(ticket.getPriority()).append(",");
            csvContent.append(ticket.getCreatedAt()).append("\n");
        }

    //    csvContent.append("\nLeads\n");
        csvContent.append("\n").append(CSV_HEADER_LEADS);

        List<Lead> leads = leadService.getCustomerLeads(customer1.getCustomerId());
        for (Lead lead : leads) {
        //    csvContent.append(lead.getLeadId()).append(",");
            csvContent.append(lead.getName()).append(",");
            csvContent.append(lead.getPhone()).append(",");
            csvContent.append(lead.getStatus()).append(",");
            csvContent.append(lead.getMeetingId()).append(",");
            csvContent.append(lead.getGoogleDrive()).append(",");
            csvContent.append(lead.getGoogleDriveFolderId()).append(",");
            csvContent.append(lead.getCreatedAt()).append("\n");
        }


        return csvContent.toString();
    }
}