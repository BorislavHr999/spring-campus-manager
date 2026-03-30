// 1. ПЪЛЕН РЕЧНИК ЗА ЦЯЛОТО ПРИЛОЖЕНИЕ
const translations = {
    bg: {
        nav_dashboard: "Главно табло", nav_profile: "Моят профил", nav_students: "Студенти", 
        nav_departments: "Факултети", nav_users: "Акаунти", nav_courses: "Специалности", 
        nav_prof: "Преподаватели", nav_clubs: "Студентски Клубове", nav_logout: "Изход",
        theme: "Тема", theme_light: "Светла тема", theme_dark: "Тъмна тема", lang_title: "Език",
        chart_title: "Разпределение на студентите", chart_students: "Разпределение на студентите", 
        recent_regs: "Последни регистрации", my_grades_title: "Моите курсове и оценки",
        loading: "Зареждане...", no_data: "Няма данни", no_students: "Няма регистрирани студенти.", chart_label: "Брой студенти",
        th_id: "ID", th_action: "Управление",
        login_subtitle: "Моля, въведете вашите данни за вход", login_error: "Грешно име или парола!", 
        login_btn: "Вход в системата", login_no_account: "Нямате акаунт?", login_register_link: "Регистрирайте се",
        reg_title: "Регистрация", reg_subtitle: "Създайте своя нов акаунт", reg_firstname: "Име", reg_lastname: "Фамилия", 
        reg_email: "Имейл", reg_username: "Потребителско име", reg_password: "Парола", reg_department: "Катедра (Факултет)", 
        reg_dept_placeholder: "-- Избери Катедра --", reg_address: "Адрес", reg_city: "Град", reg_street: "Улица", 
        reg_btn: "Регистрирай ме", reg_have_account: "Вече имате акаунт?", reg_login_link: "Влезте тук",
        prof_settings: "Настройки на профила", prof_personal_data: "Лични данни", prof_firstname_readonly: "Име (Само за четене)", 
        prof_lastname_readonly: "Фамилия (Само за четене)", prof_address_title: "Адрес за кореспонденция", 
        prof_country: "Държава", prof_save_btn: "Запази промените", prof_saving: "Записване...",
        
        all_students_title: "Всички студенти", th_student: "Студент", th_dept_address: "Катедра & Адрес", th_fac: "Фак. Номер", 
        th_enrolled_courses: "Записани курсове", btn_new_student: "Нов Студент", search_student: "Търси по име или фак. номер...", 
        details_btn: "Детайли", generating: "Генерира се...",
        users_manage_title: "Управление на Акаунти", th_username: "Потребителско име", th_role: "Роля", 
        th_linked_student: "Свързан Студент", role_admin: "АДМИН", role_user: "ПОТРЕБИТЕЛ", no_linked_student: "Няма свързан студент",
        
        th_title: "Титла", th_prof_name: "Име и Фамилия", btn_new_prof: "Нов Преподавател", search_prof: "Търси преподавател...",
        th_course_name: "Име на специалността", th_course: "Специалности", th_credits: "Кредити", th_professor: "Преподавател", th_prof: "Преподавател", th_grade: "Оценка",
        btn_new_course: "Нова Специалност", search_course: "Търси курс по име...", btn_assign_prof: "Преподавател", enroll_btn: "Запиши се",
        
        th_schedule: "График", course_schedule_title: "Учебен График", course_day: "Ден", course_day_placeholder: "Избери...", 
        course_start_time: "Начало", course_end_time: "Край",
        
        dept_manage_title: "Управление на Факултети", btn_new_dept: "Нов Факултет", th_dept_name: "Име на Факултет", 
        th_dept_office: "Офис (Кабинет)", dept_modal_title: "Данни за Факултет", dept_office_hint: "Това ще се показва в падащите менюта.",
        btn_new_club: "Нов Клуб", th_club_name: "Име на Клуба", th_club_desc: "Описание", club_modal_title: "Данни за Клуб",

        // НОВИ ЗА ИЗПИТИ И ГРАФИК
        exam_schedule_title: "Насрочи изпит:", exam_date: "Дата и час", exam_room: "Зала", 
        exam_type: "Тип на изпита", exam_type_regular: "Редовен изпит", exam_type_retake: "Поправителен изпит", 
        exam_type_midterm: "Междинен тест", exam_type_project: "Защита на проект", exam_save_btn: "Запази изпита", 
        btn_exam: "Изпит", upcoming_exams: "Предстоящи изпити", no_exams_msg: "Все още няма насрочени изпити за Вашите специалности.",
        my_schedule_title: "Седмичен График", unscheduled_courses: "Курсове без зададен час", no_classes: "Свободен ден",
        day_понеделник: "Понеделник", day_вторник: "Вторник", day_сряда: "Сряда", day_четвъртък: "Четвъртък", day_петък: "Петък"
    },
    en: {
        nav_dashboard: "Dashboard", nav_profile: "My Profile", nav_students: "Students", 
        nav_departments: "Departments", nav_users: "Accounts", nav_courses: "Courses", 
        nav_prof: "Professors", nav_clubs: "Student Clubs", nav_logout: "Logout",
        theme: "Theme", theme_light: "Light Mode", theme_dark: "Dark Mode", lang_title: "Language",
        chart_title: "Student Distribution", chart_students: "Student Distribution", 
        recent_regs: "Recent Registrations", my_grades_title: "My Courses & Grades",
        loading: "Loading...", no_data: "No data available", no_students: "No students registered.", chart_label: "Number of students",
        th_id: "ID", th_action: "Actions",
        login_subtitle: "Please enter your login details", login_error: "Invalid username or password!", 
        login_btn: "Login", login_no_account: "Don't have an account?", login_register_link: "Register here",
        reg_title: "Registration", reg_subtitle: "Create your new account", reg_firstname: "First Name", reg_lastname: "Last Name", 
        reg_email: "Email", reg_username: "Username", reg_password: "Password", reg_department: "Department", 
        reg_dept_placeholder: "-- Select Department --", reg_address: "Address", reg_city: "City", reg_street: "Street", 
        reg_btn: "Register", reg_have_account: "Already have an account?", reg_login_link: "Login here",
        prof_settings: "Profile Settings", prof_personal_data: "Personal Data", prof_firstname_readonly: "First Name (Read-only)", 
        prof_lastname_readonly: "Last Name (Read-only)", prof_address_title: "Correspondence Address", 
        prof_country: "Country", prof_save_btn: "Save Changes", prof_saving: "Saving...",
        
        all_students_title: "All Students", th_student: "Student", th_dept_address: "Department & Address", th_fac: "Faculty No.", 
        th_enrolled_courses: "Enrolled Courses", btn_new_student: "New Student", search_student: "Search by name or ID...", 
        details_btn: "Details", generating: "Generating...",
        users_manage_title: "Manage Accounts", th_username: "Username", th_role: "Role", 
        th_linked_student: "Linked Student", role_admin: "ADMIN", role_user: "USER", no_linked_student: "No linked student",
        
        th_title: "Title", th_prof_name: "Full Name", btn_new_prof: "New Professor", search_prof: "Search professor...",
        th_course_name: "Course Name", th_course: "Course", th_credits: "Credits", th_professor: "Professor", th_prof: "Professor", th_grade: "Grade",
        btn_new_course: "New Course", search_course: "Search course by name...", btn_assign_prof: "Professor", enroll_btn: "Enroll",
        
        th_schedule: "Schedule", course_schedule_title: "Class Schedule", course_day: "Day", course_day_placeholder: "Select...", 
        course_start_time: "Start", course_end_time: "End",
        
        dept_manage_title: "Manage Departments", btn_new_dept: "New Department", th_dept_name: "Department Name", 
        th_dept_office: "Office (Room)", dept_modal_title: "Department Data", dept_office_hint: "This will be shown in dropdown menus.",
        btn_new_club: "New Club", th_club_name: "Club Name", th_club_desc: "Description", club_modal_title: "Club Data",

        // NEW FOR EXAMS AND SCHEDULE
        exam_schedule_title: "Schedule Exam:", exam_date: "Date and Time", exam_room: "Room", 
        exam_type: "Exam Type", exam_type_regular: "Regular Exam", exam_type_retake: "Retake Exam", 
        exam_type_midterm: "Midterm Exam", exam_type_project: "Project Defense", exam_save_btn: "Save Exam", 
        btn_exam: "Exam", upcoming_exams: "Upcoming Exams", no_exams_msg: "No exams scheduled for your courses yet.",
        my_schedule_title: "Weekly Schedule", unscheduled_courses: "Unscheduled Courses", no_classes: "Free day",
        day_понеделник: "Monday", day_вторник: "Tuesday", day_сряда: "Wednesday", day_четвъртък: "Thursday", day_петък: "Friday"
    },
    fr: {
        nav_dashboard: "Tableau de bord", nav_profile: "Mon Profil", nav_students: "Étudiants", 
        nav_departments: "Départements", nav_users: "Comptes", nav_courses: "Cours", 
        nav_prof: "Professeurs", nav_clubs: "Clubs Étudiants", nav_logout: "Déconnexion",
        theme: "Thème", theme_light: "Mode Clair", theme_dark: "Mode Sombre", lang_title: "Langue",
        chart_title: "Répartition des étudiants", chart_students: "Répartition des étudiants", 
        recent_regs: "Inscriptions récentes", my_grades_title: "Mes Cours et Notes",
        loading: "Chargement...", no_data: "Aucune donnée", no_students: "Aucun étudiant inscrit.", chart_label: "Nombre d'étudiants",
        th_id: "ID", th_action: "Actions",
        login_subtitle: "Veuillez entrer vos identifiants", login_error: "Nom d'utilisateur ou mot de passe invalide!", 
        login_btn: "Se connecter", login_no_account: "Pas de compte?", login_register_link: "S'inscrire",
        reg_title: "Inscription", reg_subtitle: "Créez votre nouveau compte", reg_firstname: "Prénom", reg_lastname: "Nom", 
        reg_email: "E-mail", reg_username: "Nom d'utilisateur", reg_password: "Mot de passe", reg_department: "Département", 
        reg_dept_placeholder: "-- Sélectionner un département --", reg_address: "Adresse", reg_city: "Ville", reg_street: "Rue", 
        reg_btn: "S'inscrire", reg_have_account: "Déjà un compte?", reg_login_link: "Se connecter",
        prof_settings: "Paramètres du Profil", prof_personal_data: "Données Personnelles", prof_firstname_readonly: "Prénom (Lecture seule)", 
        prof_lastname_readonly: "Nom (Lecture seule)", prof_address_title: "Adresse de Correspondance", 
        prof_country: "Pays", prof_save_btn: "Enregistrer", prof_saving: "Enregistrement...",
        
        all_students_title: "Tous les étudiants", th_student: "Étudiant", th_dept_address: "Département & Adresse", th_fac: "N° d'étudiant", 
        th_enrolled_courses: "Cours inscrits", btn_new_student: "Nouvel Étudiant", search_student: "Rechercher par nom ou ID...", 
        details_btn: "Détails", generating: "Génération...",
        users_manage_title: "Gérer les Comptes", th_username: "Nom d'utilisateur", th_role: "Rôle", 
        th_linked_student: "Étudiant lié", role_admin: "ADMIN", role_user: "UTILISATEUR", no_linked_student: "Aucun étudiant lié",
        
        th_title: "Titre", th_prof_name: "Nom et Prénom", btn_new_prof: "Nouveau Professeur", search_prof: "Rechercher un professeur...",
        th_course_name: "Nom du cours", th_course: "Cours", th_credits: "Crédits", th_professor: "Professeur", th_prof: "Professeur", th_grade: "Note",
        btn_new_course: "Nouveau Cours", search_course: "Rechercher un cours...", btn_assign_prof: "Professeur", enroll_btn: "S'inscrire",
        
        th_schedule: "Emploi du temps", course_schedule_title: "Emploi du temps", course_day: "Jour", course_day_placeholder: "Sélectionner...", 
        course_start_time: "Début", course_end_time: "Fin",
        
        dept_manage_title: "Gérer les Départements", btn_new_dept: "Nouveau Département", th_dept_name: "Nom du Département", 
        th_dept_office: "Bureau (Salle)", dept_modal_title: "Données du Département", dept_office_hint: "Sera affiché dans les menus déroulants.",
        btn_new_club: "Nouveau Club", th_club_name: "Nom du Club", th_club_desc: "Description", club_modal_title: "Données du Club",

        // NOUVEAU POUR LES EXAMENS ET L'EMPLOI DU TEMPS
        exam_schedule_title: "Programmer un examen:", exam_date: "Date et Heure", exam_room: "Salle", 
        exam_type: "Type d'examen", exam_type_regular: "Examen régulier", exam_type_retake: "Examen de rattrapage", 
        exam_type_midterm: "Examen partiel", exam_type_project: "Soutenance de projet", exam_save_btn: "Enregistrer l'examen", 
        btn_exam: "Examen", upcoming_exams: "Examens à venir", no_exams_msg: "Aucun examen programmé pour vos cours pour le moment.",
        my_schedule_title: "Emploi du temps", unscheduled_courses: "Cours non programmés", no_classes: "Jour libre",
        day_понеделник: "Lundi", day_вторник: "Mardi", day_сряда: "Mercredi", day_четвъртък: "Jeudi", day_петък: "Vendredi"
    },
    es: {
        nav_dashboard: "Tablero", nav_profile: "Mi Perfil", nav_students: "Estudiantes", 
        nav_departments: "Departamentos", nav_users: "Cuentas", nav_courses: "Cursos", 
        nav_prof: "Profesores", nav_clubs: "Clubes Estudiantiles", nav_logout: "Cerrar sesión",
        theme: "Tema", theme_light: "Modo Claro", theme_dark: "Modo Oscuro", lang_title: "Idioma",
        chart_title: "Distribución de Estudiantes", chart_students: "Distribución de Estudiantes", 
        recent_regs: "Registros Recientes", my_grades_title: "Mis Cursos y Notas",
        loading: "Cargando...", no_data: "No hay datos", no_students: "No hay estudiantes registrados.", chart_label: "Número de estudiantes",
        th_id: "ID", th_action: "Acciones",
        login_subtitle: "Por favor ingrese sus datos", login_error: "¡Usuario o contraseña inválidos!", 
        login_btn: "Iniciar sesión", login_no_account: "¿No tienes una cuenta?", login_register_link: "Regístrate aquí",
        reg_title: "Registro", reg_subtitle: "Crea tu nueva cuenta", reg_firstname: "Nombre", reg_lastname: "Apellido", 
        reg_email: "Correo", reg_username: "Usuario", reg_password: "Contraseña", reg_department: "Departamento", 
        reg_dept_placeholder: "-- Seleccionar Departamento --", reg_address: "Dirección", reg_city: "Ciudad", reg_street: "Calle", 
        reg_btn: "Registrarme", reg_have_account: "¿Ya tienes cuenta?", reg_login_link: "Entra aquí",
        prof_settings: "Ajustes del Perfil", prof_personal_data: "Datos Personales", prof_firstname_readonly: "Nombre (Solo lectura)", 
        prof_lastname_readonly: "Apellido (Solo lectura)", prof_address_title: "Dirección de Correspondencia", 
        prof_country: "País", prof_save_btn: "Guardar Cambios", prof_saving: "Guardando...",
        
        all_students_title: "Todos los estudiantes", th_student: "Estudiante", th_dept_address: "Departamento y Dirección", th_fac: "Nº Matrícula", 
        th_enrolled_courses: "Cursos Inscritos", btn_new_student: "Nuevo Estudiante", search_student: "Buscar por nombre o ID...", 
        details_btn: "Detalles", generating: "Generando...",
        users_manage_title: "Gestionar Cuentas", th_username: "Usuario", th_role: "Rol", 
        th_linked_student: "Estudiante Vinculado", role_admin: "ADMIN", role_user: "USUARIO", no_linked_student: "Sin estudiante vinculado",
        
        th_title: "Título", th_prof_name: "Nombre y Apellido", btn_new_prof: "Nuevo Profesor", search_prof: "Buscar profesor...",
        th_course_name: "Nombre del Curso", th_course: "Curso", th_credits: "Créditos", th_professor: "Profesor", th_prof: "Profesor", th_grade: "Nota",
        btn_new_course: "Nuevo Curso", search_course: "Buscar curso...", btn_assign_prof: "Profesor", enroll_btn: "Inscribirse",
        
        th_schedule: "Horario", course_schedule_title: "Horario de clases", course_day: "Día", course_day_placeholder: "Seleccionar...", 
        course_start_time: "Inicio", course_end_time: "Fin",
        
        dept_manage_title: "Gestionar Departamentos", btn_new_dept: "Nuevo Departamento", th_dept_name: "Nombre del Departamento", 
        th_dept_office: "Oficina (Sala)", dept_modal_title: "Datos del Departamento", dept_office_hint: "Se mostrará en los menús desplegables.",
        btn_new_club: "Nuevo Club", th_club_name: "Nombre del Club", th_club_desc: "Descripción", club_modal_title: "Datos del Club",

        // NUEVO PARA EXÁMENES Y HORARIO
        exam_schedule_title: "Programar Examen:", exam_date: "Fecha y Hora", exam_room: "Aula", 
        exam_type: "Tipo de Examen", exam_type_regular: "Examen Regular", exam_type_retake: "Examen de Recuperación", 
        exam_type_midterm: "Examen Parcial", exam_type_project: "Defensa de Proyecto", exam_save_btn: "Guardar Examen", 
        btn_exam: "Examen", upcoming_exams: "Próximos Exámenes", no_exams_msg: "Aún no hay exámenes programados para sus cursos.",
        my_schedule_title: "Horario Semanal", unscheduled_courses: "Cursos sin programar", no_classes: "Día libre",
        day_понеделник: "Lunes", day_вторник: "Martes", day_сряда: "Miércoles", day_четвъртък: "Jueves", day_петък: "Viernes"
    },
    de: {
        nav_dashboard: "Dashboard", nav_profile: "Mein Profil", nav_students: "Studenten", 
        nav_departments: "Abteilungen", nav_users: "Benutzerkonten", nav_courses: "Kurse", 
        nav_prof: "Professoren", nav_clubs: "Studentenclubs", nav_logout: "Abmelden",
        theme: "Thema", theme_light: "Heller Modus", theme_dark: "Dunkler Modus", lang_title: "Sprache",
        chart_title: "Studentenverteilung", chart_students: "Studentenverteilung", 
        recent_regs: "Letzte Anmeldungen", my_grades_title: "Meine Kurse & Noten",
        loading: "Wird geladen...", no_data: "Keine Daten vorhanden", no_students: "Keine Studenten registriert.", chart_label: "Anzahl der Studenten",
        th_id: "ID", th_action: "Aktionen",
        login_subtitle: "Bitte geben Sie Ihre Anmeldedaten ein", login_error: "Ungültiger Benutzername oder Passwort!", 
        login_btn: "Anmelden", login_no_account: "Kein Konto?", login_register_link: "Hier registrieren",
        reg_title: "Registrierung", reg_subtitle: "Erstellen Sie Ihr neues Konto", reg_firstname: "Vorname", reg_lastname: "Nachname", 
        reg_email: "E-mail", reg_username: "Benutzername", reg_password: "Passwort", reg_department: "Abteilung", 
        reg_dept_placeholder: "-- Abteilung wählen --", reg_address: "Adresse", reg_city: "Stadt", reg_street: "Straße", 
        reg_btn: "Registrieren", reg_have_account: "Haben Sie bereits ein Konto?", reg_login_link: "Hier anmelden",
        prof_settings: "Profileinstellungen", prof_personal_data: "Persönliche Daten", prof_firstname_readonly: "Vorname (Schreibgeschützt)", 
        prof_lastname_readonly: "Nachname (Schreibgeschützt)", prof_address_title: "Korrespondenzadresse", 
        prof_country: "Land", prof_save_btn: "Änderungen speichern", prof_saving: "Speichern...",
        
        all_students_title: "Alle Studenten", th_student: "Student", th_dept_address: "Abteilung & Adresse", th_fac: "Matrikelnummer", 
        th_enrolled_courses: "Eingeschriebene Kurse", btn_new_student: "Neuer Student", search_student: "Suche nach Name oder ID...", 
        details_btn: "Details", generating: "Generieren...",
        users_manage_title: "Konten verwalten", th_username: "Benutzername", th_role: "Rolle", 
        th_linked_student: "Verknüpfter Student", role_admin: "ADMIN", role_user: "BENUTZER", no_linked_student: "Kein verknüpfter Student",
        
        th_title: "Titel", th_prof_name: "Vor- und Nachname", btn_new_prof: "Neuer Professor", search_prof: "Professor suchen...",
        th_course_name: "Kursname", th_course: "Kurs", th_credits: "Credits", th_professor: "Professor", th_prof: "Professor", th_grade: "Note",
        btn_new_course: "Neuer Kurs", search_course: "Kurs suchen...", btn_assign_prof: "Professor", enroll_btn: "Einschreiben",
        
        th_schedule: "Zeitplan", course_schedule_title: "Stundenplan", course_day: "Tag", course_day_placeholder: "Auswählen...", 
        course_start_time: "Start", course_end_time: "Ende",
        
        dept_manage_title: "Abteilungen verwalten", btn_new_dept: "Neue Abteilung", th_dept_name: "Abteilungsname", 
        th_dept_office: "Büro (Raum)", dept_modal_title: "Abteilungsdaten", dept_office_hint: "Wird in Dropdown-Menüs angezeigt.",
        btn_new_club: "Neuer Club", th_club_name: "Clubname", th_club_desc: "Beschreibung", club_modal_title: "Clubdaten",

        // NEU FÜR PRÜFUNGEN UND STUNDENPLAN
        exam_schedule_title: "Prüfung planen:", exam_date: "Datum und Uhrzeit", exam_room: "Raum", 
        exam_type: "Prüfungsart", exam_type_regular: "Reguläre Prüfung", exam_type_retake: "Wiederholungsprüfung", 
        exam_type_midterm: "Zwischenprüfung", exam_type_project: "Projektverteidigung", exam_save_btn: "Prüfung speichern", 
        btn_exam: "Prüfung", upcoming_exams: "Anstehende Prüfungen", no_exams_msg: "Es sind noch keine Prüfungen für Ihre Kurse geplant.",
        my_schedule_title: "Wochenplan", unscheduled_courses: "Nicht geplante Kurse", no_classes: "Freier Tag",
        day_понеделник: "Montag", day_вторник: "Dienstag", day_сряда: "Mittwoch", day_четвъртък: "Donnerstag", day_петък: "Freitag"
    },
    it: {
        nav_dashboard: "Bacheca", nav_profile: "Il mio profilo", nav_students: "Studenti", 
        nav_departments: "Dipartimenti", nav_users: "Account", nav_courses: "Corsi", 
        nav_prof: "Professori", nav_clubs: "Club Studenteschi", nav_logout: "Esci",
        theme: "Tema", theme_light: "Tema Chiaro", theme_dark: "Tema Scuro", lang_title: "Lingua",
        chart_title: "Distribuzione Studenti", chart_students: "Distribuzione Studenti", 
        recent_regs: "Ultime Registrazioni", my_grades_title: "I Miei Corsi e Voti",
        loading: "Caricamento...", no_data: "Nessun dato", no_students: "Nessuno studente registrato.", chart_label: "Numero di studenti",
        th_id: "ID", th_action: "Azioni",
        login_subtitle: "Inserisci i tuoi dati di accesso", login_error: "Nome utente o password non validi!", 
        login_btn: "Accedi", login_no_account: "Non hai un account?", login_register_link: "Registrati qui",
        reg_title: "Registrazione", reg_subtitle: "Crea il tuo nuovo account", reg_firstname: "Nome", reg_lastname: "Cognome", 
        reg_email: "Email", reg_username: "Nome utente", reg_password: "Password", reg_department: "Dipartimento", 
        reg_dept_placeholder: "-- Seleziona Dipartimento --", reg_address: "Indirizzo", reg_city: "Città", reg_street: "Via", 
        reg_btn: "Registrati", reg_have_account: "Hai già un account?", reg_login_link: "Accedi qui",
        prof_settings: "Impostazioni Profilo", prof_personal_data: "Dati Personali", prof_firstname_readonly: "Nome (Sola lettura)", 
        prof_lastname_readonly: "Cognome (Sola lettura)", prof_address_title: "Indirizzo di Corrispondenza", 
        prof_country: "Nazione", prof_save_btn: "Salva Modifiche", prof_saving: "Salvataggio...",
        
        all_students_title: "Tutti gli studenti", th_student: "Studente", th_dept_address: "Dipartimento e Indirizzo", th_fac: "Matricola", 
        th_enrolled_courses: "Corsi iscritti", btn_new_student: "Nuovo Studente", search_student: "Cerca per nome o ID...", 
        details_btn: "Dettagli", generating: "Generazione...",
        users_manage_title: "Gestisci Account", th_username: "Nome utente", th_role: "Ruolo", 
        th_linked_student: "Studente Collegato", role_admin: "ADMIN", role_user: "UTENTE", no_linked_student: "Nessuno studente collegato",
        
        th_title: "Titolo", th_prof_name: "Nome e Cognome", btn_new_prof: "Nuovo Professore", search_prof: "Cerca professore...",
        th_course_name: "Nome del corso", th_course: "Corso", th_credits: "Crediti", th_professor: "Professore", th_prof: "Professore", th_grade: "Voto",
        btn_new_course: "Nuovo Corso", search_course: "Cerca corso...", btn_assign_prof: "Professore", enroll_btn: "Iscriviti",
        
        th_schedule: "Orario", course_schedule_title: "Orario delle lezioni", course_day: "Giorno", course_day_placeholder: "Seleziona...", 
        course_start_time: "Inizio", course_end_time: "Fine",
        
        dept_manage_title: "Gestisci Dipartimenti", btn_new_dept: "Nuovo Dipartimento", th_dept_name: "Nome Dipartimento", 
        th_dept_office: "Ufficio (Stanza)", dept_modal_title: "Dati Dipartimento", dept_office_hint: "Verrà mostrato nei menu a discesa.",
        btn_new_club: "Nuovo Club", th_club_name: "Nome del Club", th_club_desc: "Descrizione", club_modal_title: "Dati del Club",

        // NUOVO PER ESAMI E ORARIO
        exam_schedule_title: "Programma Esame:", exam_date: "Data e Ora", exam_room: "Aula", 
        exam_type: "Tipo di Esame", exam_type_regular: "Esame Regolare", exam_type_retake: "Esame di Riparazione", 
        exam_type_midterm: "Prova Intermedia", exam_type_project: "Difesa del Progetto", exam_save_btn: "Salva Esame", 
        btn_exam: "Esame", upcoming_exams: "Prossimi Esami", no_exams_msg: "Non ci sono ancora esami programmati per i tuoi corsi.",
        my_schedule_title: "Orario Settimanale", unscheduled_courses: "Corsi non programmati", no_classes: "Giorno libero",
        day_понеделник: "Lunedì", day_вторник: "Martedì", day_сряда: "Mercoledì", day_четвъртък: "Giovedì", day_петък: "Venerdì"
    },
    ru: {
        nav_dashboard: "Главная панель", nav_profile: "Мой профиль", nav_students: "Студенты", 
        nav_departments: "Кафедры", nav_users: "Аккаунты", nav_courses: "Специальности", 
        nav_prof: "Преподаватели", nav_clubs: "Студенческие клубы", nav_logout: "Выход",
        theme: "Тема", theme_light: "Светлая тема", theme_dark: "Темная тема", lang_title: "Язык",
        chart_title: "Распределение студентов", chart_students: "Распределение студентов", 
        recent_regs: "Последние регистрации", my_grades_title: "Мои курсы и оценки",
        loading: "Загрузка...", no_data: "Нет данных", no_students: "Нет зарегистрированных студентов.", chart_label: "Количество студентов",
        th_id: "ID", th_action: "Управление",
        login_subtitle: "Пожалуйста, введите ваши данные", login_error: "Неверное имя или пароль!", 
        login_btn: "Войти", login_no_account: "Нет аккаунта?", login_register_link: "Зарегистрируйтесь здесь",
        reg_title: "Регистрация", reg_subtitle: "Создайте свой новый аккаунт", reg_firstname: "Имя", reg_lastname: "Фамилия", 
        reg_email: "Email", reg_username: "Имя пользователя", reg_password: "Пароль", reg_department: "Кафедра", 
        reg_dept_placeholder: "-- Выберите кафедру --", reg_address: "Адрес", reg_city: "Город", reg_street: "Улица", 
        reg_btn: "Зарегистрироваться", reg_have_account: "Уже есть аккаунт?", reg_login_link: "Войти здесь",
        prof_settings: "Настройки профиля", prof_personal_data: "Личные данные", prof_firstname_readonly: "Имя (Только чтение)", 
        prof_lastname_readonly: "Фамилия (Только чтение)", prof_address_title: "Адрес для корреспонденции", 
        prof_country: "Страна", prof_save_btn: "Сохранить изменения", prof_saving: "Сохранение...",
        
        all_students_title: "Все студенты", th_student: "Студент", th_dept_address: "Кафедра и Адрес", th_fac: "Номер зачетки", 
        th_enrolled_courses: "Записанные курсы", btn_new_student: "Новый Студент", search_student: "Поиск по имени или ID...", 
        details_btn: "Детали", generating: "Генерация...",
        users_manage_title: "Управление Аккаунтами", th_username: "Имя пользователя", th_role: "Роль", 
        th_linked_student: "Привязанный студент", role_admin: "АДМИН", role_user: "ПОЛЬЗОВАТЕЛЬ", no_linked_student: "Нет привязанного студента",
        
        th_title: "Должность", th_prof_name: "Имя и Фамилия", btn_new_prof: "Новый Преподаватель", search_prof: "Поиск преподавателя...",
        th_course_name: "Название курса", th_course: "Специальность", th_credits: "Кредиты", th_professor: "Преподаватель", th_prof: "Преподаватель", th_grade: "Оценка",
        btn_new_course: "Новый Курс", search_course: "Поиск курса...", btn_assign_prof: "Преподаватель", enroll_btn: "Записаться",
        
        th_schedule: "Расписание", course_schedule_title: "Учебное расписание", course_day: "День", course_day_placeholder: "Выбрать...", 
        course_start_time: "Начало", course_end_time: "Конец",
        
        dept_manage_title: "Управление кафедрами", btn_new_dept: "Новая кафедра", th_dept_name: "Название кафедры", 
        th_dept_office: "Офис (Кабинет)", dept_modal_title: "Данные кафедры", dept_office_hint: "Это будет отображаться в выпадающих списках.",
        btn_new_club: "Новый клуб", th_club_name: "Название клуба", th_club_desc: "Описание", club_modal_title: "Данные клуба",

        // НОВОЕ ДЛЯ ЭКЗАМЕНОВ И РАСПИСАНИЯ
        exam_schedule_title: "Назначить экзамен:", exam_date: "Дата и время", exam_room: "Аудитория", 
        exam_type: "Тип экзамена", exam_type_regular: "Обычный экзамен", exam_type_retake: "Пересдача", 
        exam_type_midterm: "Промежуточный тест", exam_type_project: "Защита проекта", exam_save_btn: "Сохранить экзамен", 
        btn_exam: "Экзамен", upcoming_exams: "Предстоящие экзамены", no_exams_msg: "Для ваших курсов пока нет назначенных экзаменов.",
        my_schedule_title: "Недельное расписание", unscheduled_courses: "Курсы без расписания", no_classes: "Свободный день",
        day_понеделник: "Понедельник", day_вторник: "Вторник", day_сряда: "Среда", day_четвъртък: "Четверг", day_петък: "Пятница"
    }
};

// 2. ИНЖЕКТИРАНЕ НА СВЕТЛАТА ТЕМА
const lightThemeCss = `
    [data-bs-theme="light"] body { background-color: #f4f6f9 !important; color: #212529 !important; }
    [data-bs-theme="light"] .sidebar, [data-bs-theme="light"] .table-custom, [data-bs-theme="light"] .chart-container, [data-bs-theme="light"] .modal-content { background-color: #ffffff !important; border: 1px solid #dee2e6 !important; box-shadow: 0 4px 6px rgba(0,0,0,0.05) !important; }
    [data-bs-theme="light"] .text-white, [data-bs-theme="light"] .text-light, [data-bs-theme="light"] .text-muted { color: #495057 !important; }
    [data-bs-theme="light"] .table-dark { --bs-table-bg: transparent; --bs-table-color: #212529; }
    [data-bs-theme="light"] .form-control, [data-bs-theme="light"] .form-select { background-color: #fff !important; color: #212529 !important; border-color: #ced4da !important; }
    [data-bs-theme="light"] .border-secondary { border-color: #dee2e6 !important; }
    [data-bs-theme="light"] .login-card { background-color: #ffffff !important; border: 1px solid #dee2e6 !important; }
`;
const styleSheet = document.createElement("style");
styleSheet.innerText = lightThemeCss;
document.head.appendChild(styleSheet);

// 3. ПОМОЩНА ФУНКЦИЯ ЗА ПРЕВОД НА ТЕКСТОВЕ В JAVASCRIPT
window.t = function(key) {
    const lang = localStorage.getItem('campus_lang') || 'bg';
    return (translations[lang] && translations[lang][key]) ? translations[lang][key] : key;
};

// 4. ЛОГИКА ЗА СМЯНА НА ЕЗИКА (ПРЕВЕЖДА HTML-А)
window.setLanguage = function(lang) {
    localStorage.setItem('campus_lang', lang);
    document.documentElement.lang = lang;
    
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        if (translations[lang] && translations[lang][key]) {
            if (el.tagName === 'INPUT' && el.hasAttribute('placeholder')) {
                el.placeholder = translations[lang][key];
            } else {
                el.textContent = translations[lang][key];
            }
        }
    });

    if(window.location.pathname.endsWith('index.html') || window.location.pathname === '/') {
        location.reload(); 
    } else {
        if(typeof window.loadAllStudents === 'function') window.loadAllStudents();
        if(typeof window.loadProfessors === 'function') window.loadProfessors();
        if(typeof window.loadCourses === 'function') window.loadCourses();
        if(typeof window.loadDepartments === 'function') window.loadDepartments();
        if(typeof window.loadClubs === 'function') window.loadClubs();
        if(typeof window.loadUsers === 'function') window.loadUsers();
    }
}

// 5. ЛОГИКА ЗА СМЯНА НА ТЕМАТА
window.setTheme = function(theme) {
    localStorage.setItem('campus_theme', theme);
    document.documentElement.setAttribute('data-bs-theme', theme);
}

// 6. ПЪРВОНАЧАЛНО ЗАРЕЖДАНЕ
document.addEventListener("DOMContentLoaded", function() {
    const savedTheme = localStorage.getItem('campus_theme') || 'dark';
    const savedLang = localStorage.getItem('campus_lang') || 'bg';
    
    setTheme(savedTheme);
    
    document.documentElement.lang = savedLang;
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        if (translations[savedLang] && translations[savedLang][key]) {
            if (el.tagName === 'INPUT' && el.hasAttribute('placeholder')) {
                el.placeholder = translations[savedLang][key];
            } else {
                el.textContent = translations[savedLang][key];
            }
        }
    });
});