package com.bsuir.annakhomyakova.withoutMocs;

import com.bsuir.annakhomyakova.domain.*;
import com.bsuir.annakhomyakova.domain.enumeration.Status;
import com.bsuir.annakhomyakova.repository.*;
import com.bsuir.annakhomyakova.service.MailService;
import com.bsuir.annakhomyakova.service.UserService;
import com.bsuir.annakhomyakova.web.rest.AccountResource;
import com.bsuir.annakhomyakova.web.rest.BagResource;
import com.bsuir.annakhomyakova.web.rest.FileResource;
import com.bsuir.annakhomyakova.withoutMocs.TestUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.config.JHipsterProperties;

import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {

    private static final String[] languages = {
        "ru",
        "en",
    };
    private static final Pattern PATTERN_LOCALE_3 = Pattern.compile("([a-z]{2})-([a-zA-Z]{4})-([a-z]{2})");
    private static final Pattern PATTERN_LOCALE_2 = Pattern.compile("([a-z]{2})-([a-z]{2})");
    private static final String DEFAULT_BAG_NAME = "warning";
    private static final String UPDATED_BAG_NAME = "upwarning";
    private static final String DEFAULT_DESCRIPTION = "desc";
    private static final String UPDATED_DESCRIPTION = "description";
    private static final Status DEFAULT_STATUS = Status.OPEN;
    private static final Status UPDATED_STATUS = Status.CLOSED;
    private static final String DEFAULT_LOGIN = "johndoe";
    private static final String DEFAULT_EMAIL = "johndoe@localhost";
    private static final String UPDATED_EMAIL = "ann@localhost";
    private static final String DEFAULT_FIRSTNAME = "Ann";
    private static final String DEFAULT_LASTNAME = "doe";
    private static final String DEFAULT_IMAGEURL = "http://placehold.it/50x50";
    private static final String UPDATED_IMAGEURL = "http://placehold.it/40x40";
    private static final String DEFAULT_LANGKEY = "en";
    private static final String UPDATED_LANGKEY = "fr";
    private static final String DEFAULT_NAME = "sourceCodeFile";
    private static final String UPDATED_NAME = "newFileName";
    private static final byte[] DEFAULT_SOURCE_CODE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_SOURCE_CODE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_SOURCE_CODE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SOURCE_CODE_CONTENT_TYPE = "image/png";

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TOPIC = "first";
    private static final String UPDATED_TOPIC = "second";

    private static final String DEFAULT_PHONE = "\\\\\\\\\\2168 96 9808 345 97";
    private static final String UPDATED_PHONE = "\\\\\\\\\\68541 2 570234";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BagRepository bagRepository;

    private BagAnnKh bagAnnKh;

    @Autowired
    private BagResource bagResource;

    @Autowired
    private JHipsterProperties jHipsterProperties;

    @Spy
    private JavaMailSenderImpl javaMailSender;

    @Captor
    private ArgumentCaptor<MimeMessage> messageCaptor;

    private MailService mailService;

    @Autowired
    private TaskRepository taskRepository;

    private TaskAnnKh taskAnnKh;

    @BeforeEach
    public void initTest() {
        supportAnnKh = createSupport(em);
        versionFileAnnKh = createVersionFileEntity(em);
        bagAnnKh = createBag(em);
        taskAnnKh = createTasks(em);
    }
    @Autowired
    private UserDetailsService domainUserDetailsService;

    @Autowired
    private VersionFileRepository versionFileRepository;

    @Autowired
    private EntityManager em;

    private VersionFileAnnKh versionFileAnnKh;

    @Autowired
    private UserRepository userRepository;
//
//    @Autowired
//    private Authority authority;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private SupportRepository supportRepository;

    @Autowired
    private AccountResource accountResource;

    @Autowired
    private FileResource fileResource;


    private SupportAnnKh supportAnnKh;

    public static SupportAnnKh createSupport(EntityManager em) {
        SupportAnnKh supportAnnKh = new SupportAnnKh()
            .topic(DEFAULT_TOPIC)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE)
            .description(DEFAULT_DESCRIPTION);
        return supportAnnKh;
    }

    public static SupportAnnKh createUpdatedSupportEntity(EntityManager em) {
        SupportAnnKh supportAnnKh = new SupportAnnKh()
            .topic(UPDATED_TOPIC)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .description(UPDATED_DESCRIPTION);
        return supportAnnKh;
    }
    @Test
    @Transactional
    void putNewSupport() throws Exception {
        supportRepository.saveAndFlush(supportAnnKh);
        int databaseSizeBeforeUpdate = supportRepository.findAll().size();

        SupportAnnKh updatedSupportAnnKh = supportRepository.findById(supportAnnKh.getId()).get();
        em.detach(updatedSupportAnnKh);
        updatedSupportAnnKh.topic(UPDATED_TOPIC).email(UPDATED_EMAIL).phone(UPDATED_PHONE).description(UPDATED_DESCRIPTION);

      List<SupportAnnKh> supportList = supportRepository.findAll();
        assertThat(supportList).hasSize(databaseSizeBeforeUpdate);
     SupportAnnKh testSupport = supportList.get(supportList.size() - 1);
        assertThat(testSupport.getTopic()).isEqualTo(UPDATED_TOPIC);
        assertThat(testSupport.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSupport.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSupport.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    public static VersionFileAnnKh createVersionFileEntity(EntityManager em) {
        VersionFileAnnKh versionFileAnnKh = new VersionFileAnnKh()
            .name(DEFAULT_NAME)
            .sourceCode(DEFAULT_SOURCE_CODE)
            .sourceCodeContentType(DEFAULT_SOURCE_CODE_CONTENT_TYPE)
            .creationDate(DEFAULT_CREATION_DATE);
        return versionFileAnnKh;
    }

    public static VersionFileAnnKh createUpdatedEntity(EntityManager em) {
        VersionFileAnnKh versionFileAnnKh = new VersionFileAnnKh()
            .name(UPDATED_NAME)
            .sourceCode(UPDATED_SOURCE_CODE)
            .sourceCodeContentType(UPDATED_SOURCE_CODE_CONTENT_TYPE)
            .creationDate(UPDATED_CREATION_DATE);
        return versionFileAnnKh;
    }

    @Test
    @Transactional
    void createVersionFileWithExistingId() throws Exception {
        versionFileAnnKh.setId(1L);

        int databaseSizeBeforeCreate = versionFileRepository.findAll().size();

        List<VersionFileAnnKh> versionFileList = versionFileRepository.findAll();
        assertThat(versionFileList).hasSize(databaseSizeBeforeCreate);
    }
    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = versionFileRepository.findAll().size();
        versionFileAnnKh.setName(null);

        List<VersionFileAnnKh> versionFileList = versionFileRepository.findAll();
        assertThat(versionFileList).hasSize(databaseSizeBeforeTest);
    }
    @Test
    @Transactional
    void partialUpdateVersionFileWithPatch() throws Exception {

        versionFileRepository.saveAndFlush(versionFileAnnKh);

        int databaseSizeBeforeUpdate = versionFileRepository.findAll().size();

        VersionFileAnnKh partialUpdatedVersionFileAnnKh = new VersionFileAnnKh();
        partialUpdatedVersionFileAnnKh.setId(versionFileAnnKh.getId());

        List<VersionFileAnnKh> versionFileList = versionFileRepository.findAll();
        assertThat(versionFileList).hasSize(databaseSizeBeforeUpdate);
        VersionFileAnnKh testVersionFile = versionFileList.get(versionFileList.size() - 1);
        assertThat(testVersionFile.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVersionFile.getSourceCode()).isEqualTo(DEFAULT_SOURCE_CODE);
        assertThat(testVersionFile.getSourceCodeContentType()).isEqualTo(DEFAULT_SOURCE_CODE_CONTENT_TYPE);
        assertThat(testVersionFile.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
    }
    public static UserAnnKh createEntity(EntityManager em) {
        UserAnnKh user = new UserAnnKh();
        user.setLogin(DEFAULT_LOGIN + RandomStringUtils.randomAlphabetic(5));
        user.setPassword(RandomStringUtils.random(60));
        user.setActivated(true);
        user.setEmail(RandomStringUtils.randomAlphabetic(5) + DEFAULT_EMAIL);
        user.setFirstName(DEFAULT_FIRSTNAME);
        user.setLastName(DEFAULT_LASTNAME);
        user.setImageUrl(DEFAULT_IMAGEURL);
        user.setLangKey(DEFAULT_LANGKEY);
        return user;
    }
    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileAnnKh.class);
        FileAnnKh fileAnnKh1 = new FileAnnKh();
        fileAnnKh1.setId(1L);
        FileAnnKh fileAnnKh2 = new FileAnnKh();
        fileAnnKh2.setId(fileAnnKh1.getId());
        assertThat(fileAnnKh1).isEqualTo(fileAnnKh2);
        fileAnnKh2.setId(2L);
        assertThat(fileAnnKh1).isNotEqualTo(fileAnnKh2);
        fileAnnKh1.setId(null);
        assertThat(fileAnnKh1).isNotEqualTo(fileAnnKh2);
    }
    @Test
    void equalsVerifierVersionFiles() throws Exception {
        TestUtil.equalsVerifier(VersionFileAnnKh.class);
        VersionFileAnnKh versionFileAnnKh1 = new VersionFileAnnKh();
        versionFileAnnKh1.setId(1L);
        VersionFileAnnKh versionFileAnnKh2 = new VersionFileAnnKh();
        versionFileAnnKh2.setId(versionFileAnnKh1.getId());
        assertThat(versionFileAnnKh1).isEqualTo(versionFileAnnKh2);
        versionFileAnnKh2.setId(2L);
        assertThat(versionFileAnnKh1).isNotEqualTo(versionFileAnnKh2);
        versionFileAnnKh1.setId(null);
        assertThat(versionFileAnnKh1).isNotEqualTo(versionFileAnnKh2);
    }
    public static <T> List<T> findAll(EntityManager entityManager, Class<T> clss) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clss);
        Root<T> rootEntry = criteriaQuery.from(clss);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
    @Test
    void testSendActivationEmail() throws Exception {

        UserAnnKh user = new UserAnnKh();
        user.setLangKey(Constants.DEFAULT_LANGUAGE);
        user.setLogin("anna");
        user.setEmail("khomiakova25@gmail.com");
        mailService.sendActivationEmail(user);
        verify(javaMailSender).send(messageCaptor.capture());
        MimeMessage message = messageCaptor.getValue();
        assertThat(message.getAllRecipients()[0]).hasToString(user.getEmail());
        assertThat(message.getFrom()[0]).hasToString(jHipsterProperties.getMail().getFrom());
        assertThat(message.getContent().toString()).isNotEmpty();
        assertThat(message.getDataHandler().getContentType()).isEqualTo("text/html;charset=UTF-8");
    }

    @Test
    void testSendingForAllSupportedLanguages() throws Exception {
        UserAnnKh user = new UserAnnKh();
        user.setLogin("Anna");
        user.setEmail("ann.kh@example.com");
        for (String langKey : languages) {
            user.setLangKey(langKey);
            mailService.sendEmailFromTemplate(user, "mail/testEmail", "email.test.title");
            verify(javaMailSender, atLeastOnce()).send(messageCaptor.capture());
            MimeMessage message = messageCaptor.getValue();

            String propertyFilePath = "i18n/messages_" + getJavaLocale(langKey) + ".properties";
            URL resource = this.getClass().getClassLoader().getResource(propertyFilePath);
            File file = new File(new URI(resource.getFile()).getPath());
            Properties properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));

            String emailTitle = (String) properties.get("email.test.title");
            assertThat(message.getSubject()).isEqualTo(emailTitle);
            assertThat(message.getContent().toString())
                .isEqualToNormalizingNewlines("<html>" + emailTitle + ", http://127.0.0.1:8080</html>\n");
        }
    }
    private String getJavaLocale(String langKey) {
        String javaLangKey = langKey;
        Matcher matcher2 = PATTERN_LOCALE_2.matcher(langKey);
        if (matcher2.matches()) {
            javaLangKey = matcher2.group(1) + "_" + matcher2.group(2).toUpperCase();
        }
        Matcher matcher3 = PATTERN_LOCALE_3.matcher(langKey);
        if (matcher3.matches()) {
            javaLangKey = matcher3.group(1) + "_" + matcher3.group(2) + "_" + matcher3.group(3).toUpperCase();
        }
        return javaLangKey;
    }


    @Test
    public void testingAllUsers(){
        List<UserAnnKh> users = userService.getAllPublicUsers();
        assertThat(users).isNotEmpty();
    }

    @Test
    public void registration(){

        UserAnnKh userAnnKh = new UserAnnKh();
        Authority authorityUser = new Authority();
        authorityUser.setName("ROLE_USER");
        Authority authorityAdmin = new Authority();
        authorityAdmin.setName("ROLE_ADMIN");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityUser);
        authorities.add(authorityAdmin);
        userAnnKh.setFirstName("Alexa");
        userAnnKh.setLastName("New");
        userAnnKh.setPassword("2222");
        userAnnKh.setActivated(true);
        userAnnKh.setLogin("alLogin");
        userAnnKh.setEmail("email@c.com");
        userAnnKh.setAuthorities(authorities);

        Optional<UserAnnKh> userDb = userRepository.findOneByEmailIgnoreCase(userAnnKh.getEmail());
              if(!userDb.isPresent()){
                  userRepository.save(userAnnKh);
                      UserAnnKh currentUser = entityManager.find(UserAnnKh.class,userAnnKh.getId());
                      assertThat(userAnnKh.getEmail()).isEqualTo(currentUser.getEmail());
              }
    }


    public List<String> getAuthorities(){
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

    @Test
    public void chekingAccountController(){
        assertThat(accountResource).isNotNull();
    }
    @Test
    public void uploadingFiles(){
     assertThat(fileResource).isNotNull();

    }
    public static BagAnnKh createBag(EntityManager em) {
        BagAnnKh bagAnnKh = new BagAnnKh().bagName(DEFAULT_BAG_NAME).description(DEFAULT_DESCRIPTION).status(DEFAULT_STATUS);
        return bagAnnKh;
    }

    public static BagAnnKh createUpdatedBag(EntityManager em) {
        BagAnnKh bagAnnKh = new BagAnnKh().bagName(UPDATED_BAG_NAME).description(UPDATED_DESCRIPTION).status(UPDATED_STATUS);
        return bagAnnKh;
    }


    @Test
    public void bagControl(){
        assertThat(bagResource).isNotNull();
    }
    @Test
    @Transactional
    void patchNonExistingBag() throws Exception {
        int databaseSizeBeforeUpdate = bagRepository.findAll().size();
        bagAnnKh.setId(count.incrementAndGet());

        List<BagAnnKh> bagList = bagRepository.findAll();
        assertThat(bagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void createBagWithExistingId() throws Exception {

        bagAnnKh.setId(1L);

        int databaseSizeBeforeCreate = bagRepository.findAll().size();

        List<BagAnnKh> bagList = bagRepository.findAll();
        assertThat(bagList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBag() throws Exception {
        int databaseSizeBeforeUpdate = bagRepository.findAll().size();
        bagAnnKh.setId(count.incrementAndGet());

        List<BagAnnKh> bagList = bagRepository.findAll();
        assertThat(bagList).hasSize(databaseSizeBeforeUpdate);
    }
    public static TaskAnnKh createTasks(EntityManager em) {
        TaskAnnKh taskAnnKh = new TaskAnnKh().taskName("Переопределить метод").description(DEFAULT_DESCRIPTION).deadline(LocalDate.now(ZoneId.systemDefault()));
        return taskAnnKh;
    }

    public static TaskAnnKh createUpdatedEntityTask(EntityManager em) {
        TaskAnnKh taskAnnKh = new TaskAnnKh().taskName("Добавить в новую ветку").description(UPDATED_DESCRIPTION).deadline(LocalDate.now(ZoneId.systemDefault()));
        return taskAnnKh;
    }
    @Test
    @Transactional
    void createTask() throws Exception {
        int databaseSizeBeforeCreate = taskRepository.findAll().size();

        List<TaskAnnKh> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeCreate + 1);
        TaskAnnKh testTask = taskList.get(taskList.size() - 1);
        assertThat(testTask.getTaskName()).isEqualTo("Переопределить метод");
        assertThat(testTask.getDescription()).isEqualTo("Добавить в новую ветку");
        assertThat(testTask.getDeadline()).isEqualTo(LocalDate.now(ZoneId.systemDefault()));
    }

    @Test
    @Transactional
    void createTaskWithExistingId() throws Exception {
        taskAnnKh.setId(1L);
        int databaseSizeBeforeCreate = taskRepository.findAll().size();
        List<TaskAnnKh> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTaskNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = taskRepository.findAll().size();

        taskAnnKh.setTaskName(null);

        List<TaskAnnKh> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeTest);
    }

}



