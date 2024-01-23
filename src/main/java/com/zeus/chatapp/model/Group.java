package com.zeus.chatapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_group")
public class Group {
    @Id
    @Column(name="group_id")
    private Long groupId;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER // fetches the child entities along with parent (not when required)
    )
    @JoinTable(
            name = "tbl_user_group",
            joinColumns = @JoinColumn(
                    name = "group_id",
                    referencedColumnName = "group_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "user_id"
            )
    )
    private List<User> members;

    public void addMember(User user) {
        if (members == null) {
            members = new ArrayList<>();
        }

        members.add(user);
    }
}
