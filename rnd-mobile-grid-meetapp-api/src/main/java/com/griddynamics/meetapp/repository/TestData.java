package com.griddynamics.meetapp.repository;

import com.griddynamics.meetapp.model.entity.*;
import com.griddynamics.meetapp.security.Role;
import com.griddynamics.meetapp.util.EventPublicityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;

@Component
public class TestData {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private User savedUserUser;
    private User savedSpeakerUser;
    private User savedGridUser;
    private User savedScannerUser;
    private User savedAdminUser;
    private User savedRealUser;

    private Event savedVisitedRatedEvent;
    private Event savedRegisteredEvent;
    private Event savedFavoriteEvent;
    private Event savedRealEvent;

    private Category savedMeetupCategory;
    private Category savedWebinarCategory;
    private Category savedTeachWorkshopCategory;
    private Category savedPublicDryRunCategory;
    private Category savedHackatonCategory;

    @PostConstruct
    public void init() {
        addUsers();
        addCategories();
        addEvents();
        addSpeakers();
        addRegistrations();
        addRates();
        addFavorites();
    }

    private void addCategories() {
        savedMeetupCategory = categoryRepository.save(Category.builder()
                    .title("Meetup")
                    .publicityType(EventPublicityType.PRIVATE)
                    .build());

        savedWebinarCategory = categoryRepository.save(Category.builder()
                    .title("Webinar")
                    .publicityType(EventPublicityType.PRIVATE)
                    .build());
        savedTeachWorkshopCategory = categoryRepository.save(Category.builder()
                    .title("Tech Workshop")
                    .publicityType(EventPublicityType.PRIVATE)
                    .build());
        savedPublicDryRunCategory = categoryRepository.save(Category.builder()
                    .title("Public dry-run")
                    .publicityType(EventPublicityType.PRIVATE)
                    .build());
        savedHackatonCategory = categoryRepository.save(Category.builder()
                    .title("Hackathon")
                    .publicityType(EventPublicityType.PRIVATE)
                    .build());
    }

    private void addSpeakers() {
        savedVisitedRatedEvent.getSpeakers().add(savedSpeakerUser);
        savedRegisteredEvent.getSpeakers().add(savedSpeakerUser);
        savedFavoriteEvent.getSpeakers().add(savedSpeakerUser);
        eventRepository.saveAll(new ArrayList<Event>() {{
            add(savedVisitedRatedEvent);
            add(savedRegisteredEvent);
            add(savedFavoriteEvent);
        }});
    }

    private void addFavorites() {
        savedFavoriteEvent.getFavorites().add(savedUserUser);
        eventRepository.save(savedFavoriteEvent);
    }

    private void addRegistrations() {
        registrationRepository.save(Registration.builder()
                .user(savedUserUser)
                .event(savedVisitedRatedEvent)
                .hasVisited(true)
                .build()
        );
        registrationRepository.save(Registration.builder()
                .user(savedGridUser)
                .event(savedRealEvent)
                .hasVisited(false)
                .build()
        );
        registrationRepository.save(Registration.builder()
                .user(savedUserUser)
                .event(savedRegisteredEvent)
                .hasVisited(false)
                .build()
        );

        registrationRepository.save(Registration.builder()
                .user(savedGridUser)
                .event(savedVisitedRatedEvent)
                .hasVisited(true)
                .build()
        );
        registrationRepository.save(Registration.builder()
                .user(savedGridUser)
                .event(savedRegisteredEvent)
                .hasVisited(false)
                .build()
        );
    }

    private void addRates() {
        rateRepository.save(Rate.builder()
                .user(savedUserUser)
                .event(savedVisitedRatedEvent)
                .rating(5)
                .comment("Dori me\n" +
                        "interimo Adapare Dori me\n" +
                        "Ameno Ameno Latire\n" +
                        "Latiremo\n" +
                        "Dori me")
                .build()
        );

        rateRepository.save(Rate.builder()
                .user(savedGridUser)
                .event(savedVisitedRatedEvent)
                .rating(4)
                .comment("Dori me\n" +
                        "interimo Adapare Dori me\n" +
                        "Ameno Ameno Latire\n" +
                        "Latiremo\n" +
                        "Dori me")
                .build()
        );

        rateRepository.save(Rate.builder()
                .user(savedGridUser)
                .event(savedRealEvent)
                .rating(5)
                .comment("Awesome! Thanks Iryna!")
                .build()
        );
    }

    private void addUsers() {
        savedUserUser = userRepository.save(User.builder()
                .email("user@domain.com")
                .name("Jack Daniels")
                .password("password")
                .phoneNumber("123456789")
                .role(Role.USER)
                .authenticationType(User.AuthenticationType.CUSTOM)
                .build()
        );
        savedSpeakerUser = userRepository.save(User.builder()
                .email("speaker@domain.com")
                .name("Sherlock Holmes")
                .password("password")
                .phoneNumber("123456789")
                .position("Manager")
                .experiencedIn("Management")
                .photo("https://www.meme-arsenal.com/memes/34996c8b3d6d35bc9a08bb20a95df8e0.jpg")
                .role(Role.GRID)
                .authenticationType(User.AuthenticationType.CUSTOM)
                .build()
        );
        savedRealUser = userRepository.save(User.builder()
                .email("ikikalo@griddynamics.com")
                .password("password")
                .name("Iryna Kikalo")
                .phoneNumber("+380913550225")
                .photo("https://i.imgur.com/lfQYD6e.jpg")
                .position("HR Lead")
                .experiencedIn("Recruiting")
                .role(Role.GRID)
                .authenticationType(User.AuthenticationType.CUSTOM)
                .build()
        );
        savedGridUser = userRepository.save(User.builder()
                .email("grid@domain.com")
                .password("password")
                .name("David Bowie")
                .phoneNumber("123456789")
                .position("Manager")
                .experiencedIn("Management")
                .photo("https://www.meme-arsenal.com/memes/34996c8b3d6d35bc9a08bb20a95df8e0.jpg")
                .role(Role.GRID)
                .authenticationType(User.AuthenticationType.CUSTOM)
                .build()
        );

        savedScannerUser = userRepository.save(User.builder()
                .email("scanner@domain.com")
                .name("Frank Sinatra")
                .password("password")
                .phoneNumber("123456789")
                .position("Admin")
                .experiencedIn("Administration")
                .photo("https://lh3.googleusercontent.com/proxy/1ZzKGpsNTIFePdjnfszfGH5NkbyyhrgbniOZR7i7TA54c9C_CmLtM-EaTEuHBzU61cvTTCmw4tBvHTl70oyryDu_o5L4pj4SWbDq_Osu2pPSRl2_1pTbUnpdqRA_DsgLI0j8x3RCP7y6zRQUwAjFM2HcxhJUw_L98Sw6sDtlp-HOl85KmGTau0CClZEMnIYGk47oOCzKI_G4fYZnk8Y")
                .role(Role.SCANNER)
                .authenticationType(User.AuthenticationType.CUSTOM)
                .build()
        );
        savedAdminUser = userRepository.save(User.builder()
                .email("admin@domain.com")
                .name("Gandalf the White")
                .password("password")
                .phoneNumber("123456789")
                .position("Admin")
                .experiencedIn("Administration")
                .photo("https://thumbs.dreamstime.com/b/admin-sign-laptop-icon-stock-vector-166205404.jpg")
                .role(Role.ADMIN)
                .authenticationType(User.AuthenticationType.CUSTOM)
                .build()
        );
    }

    private void addEvents() {
        savedRealEvent = eventRepository.save(Event.builder()
                .title("Emotional intelligence")
                .category(savedWebinarCategory)
                // 28 of May 2020 14:00 MSK
                .startDate(new Date(1590663600000L))
                .description("Hello everyone! \n" +
                        "Iryna Kikalo hosts the second part of the webinar Emotional intelligence and leadership by popular demand!\n" +
                        "This meeting will be interesting for current and future leaders, as well as for all those who would like to learn a little bit more about what is behind the concept of “emotional intelligence”. Based on the model of Daniel Goleman, we will talk about social sensitivity and empathy and that all this can be applied in a working environment. Next we will stop on the leadership style and their usages.")
                .address("https://griddynamics.zoom.us/j/94770229206")
                .image("https://i.imgur.com/EdflTlf.png")
                .speakers(new ArrayList<User>() {{
                    add(savedRealUser);
                }})
                .favorites(new ArrayList<>())
                .build()
        );

        eventRepository.save(Event.builder()
                .title("Test meetup 2")
                .category(savedHackatonCategory)
                .startDate(new Date())
                .description("Some blank text here. Nevermind. Keep calm and scroll down")
                .address("Tibet")
                .image("https://thereklama.com/wp/wp-content/uploads/2018/03/tibet-palace-678x381.jpg")
                .speakers(new ArrayList<>())
                .favorites(new ArrayList<>())
                .build()
        );

        savedVisitedRatedEvent = eventRepository.save(Event.builder()
                .title("Test meetup visited & rated")
                .category(savedHackatonCategory)
                .startDate(new Date())
                .description("This is visited & rated event. Some blank text here. Nevermind. Keep calm and scroll down")
                .address("Empire State Building")
                .image("https://fort.kh.ua/wp-content/uploads/2018/05/Empire-State-Building-1.png")
                .speakers(new ArrayList<>())
                .favorites(new ArrayList<>())
                .build()
        );

        savedRegisteredEvent = eventRepository.save(Event.builder()
                .title("Test meetup registered")
                .category(savedTeachWorkshopCategory)
                .startDate(new Date())
                .description("This is registered event. Some blank text here. Nevermind. Keep calm and scroll down")
                .address("Hollywood")
                .image("https://media-cdn.tripadvisor.com/media/photo-m/1280/17/23/bc/50/hollywood-sign.jpg")
                .speakers(new ArrayList<>())
                .favorites(new ArrayList<>())
                .build()
        );

        savedFavoriteEvent = eventRepository.save(Event.builder()
                .title("Test meetup favorite")
                .category(savedWebinarCategory)
                .startDate(new Date())
                .description("This is favorite event. Some blank text here. Nevermind. Keep calm and scroll down")
                .address("Buckingham Palace")
                .speakers(new ArrayList<>())
                .favorites(new ArrayList<>())
                .image("https://i0.wp.com/metro.co.uk/wp-content/uploads/2019/10/DMGTCHPDPICT000434649790-3a48.jpg?quality=90&strip=all&zoom=1&resize=644%2C338&ssl=1")
                .build()

        );
    }
}