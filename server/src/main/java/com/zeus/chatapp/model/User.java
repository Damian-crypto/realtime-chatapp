package com.zeus.chatapp.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate that is normally associated with simple POJOs (getters, setters, toString, etc)
@Entity //  it is assumed that this entity is mapped to a table named User in db
@Builder // applies builder pattern
@NoArgsConstructor // will generate a constructor with no parameters
@AllArgsConstructor // generates a constructor with 1 parameter for each field in your class
@Table(
        name = "tbl_user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_mobile_username",
                        columnNames = { "mobileNo", "username" }
                )
        }
)
public class User {
    @Id // userId is the primary-key of this table
    @Column(name = "user_id") // to specify a name of a table column
//     @SequenceGenerator( // a primary key generator
//             name = "user_sequence",
//             sequenceName = "user_sequence",
//             allocationSize = 1 // specify the amount by which the sequence value should be incremented when obtaining a block of values from the database sequence
//     )
//     @GeneratedValue( // specification of generation strategies
//             strategy = GenerationType.SEQUENCE, // sequence of numbers
//             generator = "user_sequence"
//     )
    private Long userId;
    private String name;
    private String email;
    @Column(
            name = "mobile_no",
            nullable = false // to indicate that mobile field cannot be null
    )
    private String mobileNo;
    private Date lastOnline;

    // Spring Security need these...
    @Column(
        name = "username",
        nullable = false
    )
    private String username;
    @Column(
        name = "authority"
    )
    private String authority;
    private String password;
    private boolean enabled;
}
