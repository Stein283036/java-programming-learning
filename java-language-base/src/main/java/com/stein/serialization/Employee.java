package com.stein.serialization;

class Employee implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private transient int SSN; // transient 修饰的字段不会参与序列化过程，在反序列化的时候会被赋予该类型的默认值。
    private String number;
    private Address address; // 对于引用类型需要被序列化的话那么也需要实现序列化接口，否则序列化和反序列时都会抛出异常，导致序列化过程失败。

    public void mailCheck() {
        System.out.println("Mailing a check to " + name + " " + address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", SSN=" + SSN +
                ", number='" + number + '\'' +
                ", address=" + address +
                '}';
    }
}
