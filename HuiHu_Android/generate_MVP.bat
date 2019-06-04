

@echo off
set bNull=0
set rootPath=%cd%
@echo %rootPath%
if "%1"=="" set bNull=1
if "%2"=="" set bNull=1
if "%3"=="" set bNull=1
if "%bNull%"=="1" (goto null_data) else ( if exist %rootPath% (goto exist_data) else ( goto module_null) )

:exist_data
set module_name=%1
set package_name=%2
call :lowercase %3 var
set lowercase_class_name=%var%
set package_path=%package_name:.=\%
set relative_path=%package_path%\%lowercase_class_name%
@echo %lowercase_class_name%

if "%5" == "-M" (set current_path=%module_name%\src\main\java\%package_path%) else (set current_path=%module_name%\src\main\java\%relative_path%)

if "%5" == "-M" (set package=%2) else (set package=%2.%lowercase_class_name%)

if "%5" == "-M" (set mergeInterfacePath=mvpinterface\%lowercase_class_name%interface) else (set mergeInterfacePath=%lowercase_class_name%interface)

if "%5" == "-M" (set mergeInterfacePackage=mvpinterface.%lowercase_class_name%interface) else (set mergeInterfacePackage=%lowercase_class_name%interface)

@echo %relative_path%
@echo %current_path%


md %current_path%
md %current_path%\view
md %current_path%\presenter
md %current_path%\model
md %current_path%\entity
md %current_path%\%mergeInterfacePath%

if "%4"=="-F" (
(
echo package %package%.view;
echo.
echo import android.support.v4.app.Fragment;
echo.
echo import %package%.%mergeInterfacePackage%.I%3Presenter;
echo import %package%.%mergeInterfacePackage%.I%3View;
echo import %package%.presenter.Imp%3Presenter;
echo.
echo public class %3Fragment extends Fragment implements I%3View{
echo     private final I%3Presenter i%3Presenter = new Imp%3Presenter^(this^);
echo.
echo }
)>>%current_path%\view\%3Fragment.java
)

if "%4"=="-A" (
(
echo package %package%.view;
echo.
echo import android.support.v4.app.FragmentActivity;
echo.
echo import %package%.%mergeInterfacePackage%.I%3Presenter;
echo import %package%.%mergeInterfacePackage%.I%3View;
echo import %package%.presenter.Imp%3Presenter;
echo.
echo public class %3Activity extends FragmentActivity implements I%3View{
echo     private final I%3Presenter i%3Presenter = new Imp%3Presenter^(this^);
echo.
echo }
)>>%current_path%\view\%3Activity.java
)

(
echo package %package%.presenter;
echo.
echo import %package%.%mergeInterfacePackage%.I%3Model;
echo import %package%.%mergeInterfacePackage%.I%3Presenter;
echo import %package%.%mergeInterfacePackage%.I%3View;
echo import %package%.model.Imp%3Model;
echo.
echo public class Imp%3Presenter implements I%3Presenter {
echo     private final I%3Model i%3Model = new Imp%3Model^(this^);
echo     private final I%3View i%3View;
echo.
echo     public Imp%3Presenter^(I%3View i%3View^) {
echo         this.i%3View = i%3View;
echo     }
echo }
)>>%current_path%\presenter\Imp%3Presenter.java

(
echo package %package%.model;
echo.
echo import %package%.%mergeInterfacePackage%.I%3Model;
echo import %package%.%mergeInterfacePackage%.I%3Presenter;
echo.
echo public class Imp%3Model implements I%3Model {
echo     private final I%3Presenter i%3Presenter;
echo.
echo     public Imp%3Model^(I%3Presenter i%3Presenter^) {
echo         this.i%3Presenter = i%3Presenter;
echo     }
echo }
)>>%current_path%\model\Imp%3Model.java

(
echo package %package%.%mergeInterfacePackage%;
echo.
echo public interface I%3View {
echo.
echo }
)>>%current_path%\%mergeInterfacePath%\I%3View.java

(
echo package %package%.%mergeInterfacePackage%;
echo.
echo public interface I%3Presenter {
echo.
echo }
)>>%current_path%\%mergeInterfacePath%\I%3Presenter.java

(
echo package %package%.%mergeInterfacePackage%;
echo.
echo public interface I%3Model {
echo.
echo }
)>>%current_path%\%mergeInterfacePath%\I%3Model.java

@echo Generate [%2] [%3]'s VPM related documents is finish.
@echo Attention: (1) If you can not see the [%2] in IDE, please check whether the generation was successful and try refresh by manually
@echo            (2) Maybe you need to manually change file encoding to UTF-8 (for CI)
@echo            (3) If you add new Activity, you should manually update AndroidManifest.xml to bind
goto :eof

:module_null
@echo Module[%1] is not exist.
goto :eof

:null_data
@echo Please input the right format.
@echo Format: generate_viper [Package Name] [Scenes Name] [UI type]
@echo PS:Module must be exist.
@echo PS:UI type use -A(for Activity) -F(for Fragment)
goto :eof

:lowercase
setlocal enabledelayedexpansion&set $=&set "#=@%~1"
for %%a in (a b c d e f g h i j k l m n o p q r s t u v w x y z)do set #=!#:%%a=%%a!
endlocal&set "%~2=%#:~1%"&exit/b