package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.*;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/info")
@CrossOrigin(origins = "http://localhost:3000")
public class InfoController {
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentStatusRepository paymentStatusRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @RequestMapping(value = "manufacturer", method = RequestMethod.GET)
    public ResponseEntity<Set<Manufacturer>> getManufacturers() {
        Set<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return ResponseEntity.ok().body(manufacturers);
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public ResponseEntity<Set<Category>> getCategories() {
        Set<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @RequestMapping(value = "sub_category", method = RequestMethod.GET)
    public ResponseEntity<Set<SubCategory>> getSubCategories() {
        Set<SubCategory> subCategories = subCategoryRepository.findAll();
        return ResponseEntity.ok().body(subCategories);
    }

    @RequestMapping(value = "group", method = RequestMethod.GET)
    public ResponseEntity<Set<Group>> getGroups() {
        Set<Group> groups = groupRepository.findAll();
        return ResponseEntity.ok().body(groups);
    }

    @RequestMapping(value = "delivery", method = RequestMethod.GET)
    public ResponseEntity<Set<Delivery>> getDeliveries() {
        Set<Delivery> deliveries = deliveryRepository.findAll();
        return ResponseEntity.ok().body(deliveries);
    }

    @RequestMapping(value = "order_status", method = RequestMethod.GET)
    public ResponseEntity<Set<OrderStatus>> getOrderStatuses() {
        Set<OrderStatus> orderSatuses = orderStatusRepository.findAll();
        return ResponseEntity.ok().body(orderSatuses);
    }

    @RequestMapping(value = "payment", method = RequestMethod.GET)
    public ResponseEntity<Set<Payment>> getPayments() {
        Set<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.ok().body(payments);
    }

    @RequestMapping(value = "payment_status", method = RequestMethod.GET)
    public ResponseEntity<Set<PaymentStatus>> getPaymentStatuses() {
        Set<PaymentStatus> paymentSatuses = paymentStatusRepository.findAll();
        return ResponseEntity.ok().body(paymentSatuses);
    }

    @RequestMapping(value = "manufacturer", method = RequestMethod.POST)
    public void addManufacturer(@RequestParam String name) {
        Set<Manufacturer> manufacturers = manufacturerRepository.findAll();
        for (Manufacturer m : manufacturers) {
            if (m.getName() == name) {
                throw new AlreadyExistsException("Such manufacturer name already exists.");
            }
        }
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturerRepository.save(manufacturer);
    }

    @RequestMapping(value = "category", method = RequestMethod.POST)
    public void addCategory(@RequestParam String name) {
        Set<Category> categories = categoryRepository.findAll();
        for (Category c : categories) {
            if (c.getName() == name) {
                throw new AlreadyExistsException("Such category name already exists.");
            }
        }
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }

    @RequestMapping(value = "sub_category", method = RequestMethod.POST)
    public void addSubCategory(@RequestParam int id,
                               @RequestParam String name) {
        Set<SubCategory> subCategories = subCategoryRepository.findAll();
        for (SubCategory sc : subCategories) {
            if (sc.getName() == name) {
                throw new AlreadyExistsException("Such subcategory name already exists.");
            }
        }
        subCategories = subCategoryRepository.findAllByCategoryId(id);
        if (subCategories == null) {
            throw new NotFoundException("Wrong category ID.");
        }
        SubCategory subCategory = new SubCategory();
        subCategory.setName(name);
        subCategory.setCategoryId(id);
        subCategoryRepository.save(subCategory);
    }

    @RequestMapping(value = "group", method = RequestMethod.POST)
    public void addGroup(@RequestParam String name) {
        Set<Group> groups = groupRepository.findAll();
        for (Group g : groups) {
            if (g.getName() == name) {
                throw new AlreadyExistsException("Such group name already exists.");
            }
        }
        Group group = new Group();
        group.setName(name);
        groupRepository.save(group);
    }

    @RequestMapping(value = "delivery", method = RequestMethod.POST)
    public void addDelivery(@RequestParam String name) {
        Set<Delivery> deliveries = deliveryRepository.findAll();
        for (Delivery d : deliveries) {
            if (d.getName() == name) {
                throw new AlreadyExistsException("Such delivery name already exist.");
            }
        }
        Delivery delivery = new Delivery();
        delivery.setName(name);
        deliveryRepository.save(delivery);
    }

    @RequestMapping(value = "order_status", method = RequestMethod.POST)
    public void addOrderStatus(@RequestParam String name) {
        Set<OrderStatus> statuses = orderStatusRepository.findAll();
        for (OrderStatus os : statuses) {
            if (os.getName() == name) {
                throw new AlreadyExistsException("Such order status already exists.");
            }
        }
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setName(name);
        orderStatusRepository.save(orderStatus);
    }

    @RequestMapping(value = "payment", method = RequestMethod.POST)
    public void addPayment(@RequestParam String name) {
        Set<Payment> payments = paymentRepository.findAll();
        for (Payment p : payments) {
            if (p.getName() == name) {
                throw new AlreadyExistsException("Such payment name already exists.");
            }
        }
        Payment payment = new Payment();
        payment.setName(name);
        paymentRepository.save(payment);
    }

    @RequestMapping(value = "payment_status", method = RequestMethod.POST)
    public void addPaymentStatus(@RequestParam String name) {
        Set<PaymentStatus> statuses = paymentStatusRepository.findAll();
        for (PaymentStatus ps : statuses) {
            if (ps.getStatus() == name) {
                throw new AlreadyExistsException("Such payment status name already exists.");
            }
        }
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setStatus(name);
        paymentStatusRepository.save(paymentStatus);
    }

    @RequestMapping(value = "manufacturer", method = RequestMethod.DELETE)
    public void deleteManufacturer(@RequestParam int id) {
        Manufacturer manufacturer = manufacturerRepository.getManufacturerById(id);
        if (manufacturer == null) {
            throw new NotFoundException("Wrong manufacturer ID.");
        }
        manufacturerRepository.delete(manufacturer);
    }

    @RequestMapping(value = "category", method = RequestMethod.DELETE)
    public void deleteCategory(@RequestParam int id) {
        Category category = categoryRepository.getCategoryById(id);
        if (category == null) {
            throw new NotFoundException("Wrong category ID.");
        }
        Set<SubCategory> subCategories = subCategoryRepository.findAllByCategoryId(id);
        for (SubCategory sc : subCategories) {
            subCategoryRepository.delete(sc);
        }
        categoryRepository.delete(category);
    }

    @RequestMapping(value = "sub_category", method = RequestMethod.DELETE)
    public void deleteSubCategory(@RequestParam String name) {
        Set<SubCategory> subCategories = subCategoryRepository.findAll();
        SubCategory subCategory = null;
        for (SubCategory sc : subCategories) {
            if (sc.getName() == name) {
                subCategory = sc;
                break;
            }
        }
        if (subCategory == null) {
            throw new NotFoundException("Wrong subcategory name.");
        }
        subCategoryRepository.delete(subCategory);
    }

    @RequestMapping(value = "group", method = RequestMethod.DELETE)
    public void deleteGroup(@RequestParam int id) {
        Group group = groupRepository.getGroupById(id);
        if (group == null) {
            throw new NotFoundException("Wrong group ID.");
        }
        groupRepository.delete(group);
    }

    @RequestMapping(value = "delivery", method = RequestMethod.DELETE)
    public void deleteDelivery(@RequestParam int id) {
        Delivery delivery = deliveryRepository.getDeliveryById(id);
        if (delivery == null) {
            throw new NotFoundException("Wrong delivery ID.");
        }
        deliveryRepository.delete(delivery);
    }

    @RequestMapping(value = "order_status", method = RequestMethod.DELETE)
    public void deleteOrderStatus(@RequestParam int id) {
        OrderStatus os = orderStatusRepository.getOrderStatusById(id);
        if (os == null) {
            throw new NotFoundException("Wrong order status ID.");
        }
        orderStatusRepository.delete(os);
    }

    @RequestMapping(value = "payment", method = RequestMethod.DELETE)
    public void deletePayment(@RequestParam int id) {
        Payment payment = paymentRepository.getPaymentById(id);
        if (payment == null) {
            throw new NotFoundException("Wrong payment ID.");
        }
        paymentRepository.delete(payment);
    }

    @RequestMapping(value = "payment_status", method = RequestMethod.DELETE)
    public void deletePaymentStatus(@RequestParam int id) {
        PaymentStatus ps = paymentStatusRepository.getPaymentStatusById(id);
        if (ps == null) {
            throw new NotFoundException("Wrong payment status ID.");
        }
        paymentStatusRepository.delete(ps);
    }

}