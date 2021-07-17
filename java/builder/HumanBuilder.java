package builder;

public interface HumanBuilder {
    HumanBuilder setFirstName(String firstName);

    HumanBuilder setLastName(String lastName);

    HumanBuilder setMiddleName(String middleName);

    HumanBuilder setCountry(String country);

    HumanBuilder setAddress(String address);

    HumanBuilder setPhone(String phone);

    HumanBuilder setAge(int age);

    HumanBuilder setGender(String gender);

    Human build();
}
