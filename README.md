Для запуска нужно перейти в директорию проекта и прописать в командной строке/терминале следующее:
./mvnw -pl backend spring-boot:run && ./mvnw -pl frontend clean install

WIN:mvnw.cmd -pl backend spring-boot:run && mvnw.cmd -pl frontend clean install


Перед запуском удостоверьтесь, что указали логин и пароль к бд в ресурсах backend.
