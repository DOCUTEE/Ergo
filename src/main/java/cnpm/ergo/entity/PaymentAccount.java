package cnpm.ergo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_account")
public class PaymentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankId")
    private int bankId;

    @Column(name = "type", columnDefinition = "NVARCHAR(100) NOT NULL")
    private String type;

    @Column(name = "serialNumber", columnDefinition = "NVARCHAR(50) NOT NULL")
    private String serialNumber;

    @Column(name = "bank", columnDefinition = "NVARCHAR(100) NOT NULL")
    private String bank;

    // Getters and Setters
    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
