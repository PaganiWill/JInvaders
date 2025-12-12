@echo off
echo Compilando JInvaders...

REM Limpar build anterior
if exist build rmdir /s /q build
mkdir build\classes

REM Verificar se há JAR de dependências
set CLASSPATH=
if exist "src\main\resources\libs\LinuxJoystick.jar" (
    set CLASSPATH=src\main\resources\libs\LinuxJoystick.jar
)

REM Compilar código Java
echo Compilando código Java...
javac -source 1.8 -target 1.8 -d build\classes -cp "src\main\java;%CLASSPATH%" -sourcepath src\main\java src\main\java\org\opensourcearcade\jinvaders\*.java src\main\java\org\opensourcearcade\jinvaders\entities\*.java src\main\java\org\opensourcearcade\jinvaders\joystick\*.java
if errorlevel 1 (
    echo Erro ao compilar!
    pause
    exit /b 1
)

REM Copiar recursos
echo Copiando recursos...
xcopy /E /I /Y src\main\resources\* build\classes\ >nul 2>&1

REM Criar JAR
echo Criando JAR...
cd build\classes

REM Procurar jar.exe
set JAR_CMD=
for /f "delims=" %%i in ('powershell -Command "Get-ChildItem 'C:\Program Files\Java' -Recurse -Filter 'jar.exe' -ErrorAction SilentlyContinue | Select-Object -First 1 -ExpandProperty FullName"') do set JAR_CMD=%%i

if not defined JAR_CMD (
    REM Tentar usar jar diretamente (se estiver no PATH)
    where jar >nul 2>&1
    if errorlevel 1 (
        echo.
        echo ERRO: Não foi possível encontrar o jar.exe
        echo Verifique se o JDK está instalado em C:\Program Files\Java
        echo.
        cd ..\..
        pause
        exit /b 1
    )
    set JAR_CMD=jar
)

"%JAR_CMD%" cfm ..\..\jinvaders-java.jar ..\..\MANIFEST.MF *
if errorlevel 1 (
    echo.
    echo ERRO: Falha ao criar o JAR
    echo.
    cd ..\..
    pause
    exit /b 1
)
cd ..\..

REM Limpar build temporário
if exist build rmdir /s /q build

echo.
echo ========================================
echo JAR gerado com sucesso: jinvaders-java.jar
echo ========================================
pause

