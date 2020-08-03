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
