@echo off
echo Mengkompilasi program (Struktur Paket Baru)...
javac -d bin -cp "lib\mysql-connector-j-8.0.33.jar" -sourcepath . freshflowerfinal\Main.java freshflowerfinal\DBFixer.java
if %errorlevel% neq 0 (
    echo Kompilasi gagal!
    pause
    exit /b %errorlevel%
)
echo Menjalankan program...
java -cp "bin;lib\mysql-connector-j-8.0.33.jar" freshflowerfinal.Main
pause
