# Dagger 사용하기

## AppComponent 생성하기

```java
@Singleton
@Component(modules = {
        AndroidInjectionModule.class, //Application 과의 연결을 도울 AndroidInjector 를 상속
        AppModule.class,              //Application 에서 사용할 모듈을 상속
        ActivityBuilder.class         //Application 에서 사용할 Activity 들을 상속
})
public interface AppComponent {

    void inject(ApayStoreApp app);

    @Component.Builder
    interface Builder {               // Application 과의 연결을 도울 Builder 를 정의

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

```

## AppModule 생성하기

```java
@Module
public class AppModule {
    
    //Context 타입의 의존성 객체를 생성
    @Provides
    @Singleton
    Context provideContext(Application application) {   
        return application;
    }
}

```

## ActivityBuilder 생성하기

```java
@Module
public abstract class ActivityBuilder {

    //Application 에서 사용할 Activity
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
}

```

## ViewModelProviderFactory 생성하기

```java
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

//    private final DataManager dataManager;

    @Inject
    public ViewModelProviderFactory() {
//        this.dataManager = dataManager;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel();
        }

        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass.getName());
    }
}

```

## Base Application 생성하기

```java
// DaggerApplication 를 상속받고, ApplicationComponent 에서 정의한 Builder 를 활용하여 Component 와 연결
public class ApayStoreApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }
}

```

## MainActivity 에서 ViewModel 주입받기

```java
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Inject
    ViewModelProviderFactory factory;
    
    private MainViewModel mMainViewModel;

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        return mMainViewModel;
    }
}

```

# Retrofit 사용하기

## Request / Response 에 사용할 Model 정의하기

### login request

```java
public class LoginRequest {

    @Expose
    @SerializedName("loginId")
    private String loginId;

    @Expose
    @SerializedName("password")
    private String password;

    public LoginRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }
}

```

### login response

```java
public class LoginResponse {

    @Expose
    @SerializedName("data")
    private Login data;

    public Login getData() { return data; }

    public static class Login {

        @Expose
        @SerializedName("jwtToken")
        private String jwtToken;

        @Expose
        @SerializedName("userId")
        private int userId;

        public String getJwtToken() {
            return jwtToken;
        }

        public int getUserId() {
            return userId;
        }
    }
}

```

## RepoService 생성하기

```java
public interface RepoService {

    @POST("user/login")
    @Headers("No-Authentication: true")
    Single<LoginResponse> doLoginCall(@Body LoginRequest loginRequest);
}

```

## DataManager 생성하기

```java
public interface DataManager extends RepoService {

    enum LoggedInMode {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}

```

## AppDataManager 생성하기

```java
@Singleton
public class AppDataManager implements DataManager {

    private final RepoService mRepoService;

    @Inject
    public AppDataManager(RepoService mRepoService) {
        this.mRepoService = mRepoService;
    }

    @Override
    public Single<LoginResponse> doLoginCall(LoginRequest loginRequest) {
        return mRepoService.doLoginCall(loginRequest);
    }
}

```

# DataManager 주입받기

## AppModule 에 DataManager 추가하기
```java
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    RepoService provideRepoService(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }
}

```

# SplashActivity 생성하기

## 용도

처음 사용자가 앱을 구동하였을 때 보이는 화면으로, 이미지나 로고 등으로 앱을 간단하게 표현할 수 있다. 또한 로그인 여부를 확인하여
SplashActivity 이후의 Activity 를 결정할 수 있다. (LoginActivity OR MainActivity)

## 개발

### Activity 생성

- `SplashActivity.java`

### UI

- `activity_splash.xml`

### ViewModel

- `SplashViewModel.java`