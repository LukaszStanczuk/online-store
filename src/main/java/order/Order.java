package order;

import com.onlinestore.orderLine.OrderLine;
import com.onlinestore.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String totalCost;
    String orderAddress;
    String userAddress;
    LocalDateTime orderDate;
    OrderStatus orderStatus;
    //@OneToMany(mappedBy = "order")
    //private List<OrderLine> orderLine;
    //@OneToOne
   // User user;




}
