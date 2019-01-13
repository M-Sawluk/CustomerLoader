package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CustomerContact {
    private final Long id;
    private final Long customer_id;
    private final String contact;
    private final ContactType contactType;
}
