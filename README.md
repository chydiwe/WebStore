Чтобы тестить наши серваки нужно:
backend:https://www.jetbrains.com/idea/
frontend:https://www.jetbrains.com/webstorm/
После уставноки вы нажимаете "Check out from Version Control"
вставляете там ссылку:https://github.com/LonelyDude/WebStore.git
Для запуска BACKEND сервера должена быть java8 и если он просит выбрать сдк выбираете 8 джаву после запускаете sql сервер и в файле application.properties прописываете свой логин и пароль и открываете консоль sql где нужно также залогинться и вставить в нее текст из table.txt до скриптов или(можно зайти в mysql и сделать так mysql> source file_name где file_ame путь к tables.txt) после зайти WebStoreApplication и нажать треугольник перед main(остальные вопросы к Руслану)
Для запуска FRONTEND сервера должен быть установлен npm(https://nodejs.org/en/) если WEBSTORM просит указать путь до node то указываете путь до Node.js command prompt который вы можете найти в меню пуск
После заходите через webStorm в папку FrontENd для первого запуска прописываете в консоле npm install
а для последующих запусков npm start (Ну по этой херне вопросы ко мне) 
!FAQ
1)Если нет git скачай гит https://git-scm.com/downloads
2)Тема idea!=idea