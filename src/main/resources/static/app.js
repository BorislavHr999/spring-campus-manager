// 1. ПЪЛЕН РЕЧНИК ЗА ЦЯЛОТО ПРИЛОЖЕНИЕ
const translations = {
    bg: {
        // Меню и Настройки
        nav_dashboard: "Главно табло", nav_profile: "Моят профил", nav_students: "Студенти", 
        nav_departments: "Факултети", nav_users: "Акаунти", nav_courses: "Специалности", 
        nav_prof: "Преподаватели", nav_clubs: "Студентски Клубове", nav_logout: "Изход",
        theme: "Тема", theme_light: "Светла тема", theme_dark: "Тъмна тема", lang_title: "Език",
        
        // Главно табло
        chart_title: "Разпределение на студентите", chart_students: "Разпределение на студентите", 
        recent_regs: "Последни регистрации", my_grades_title: "Моите курсове и оценки",
        loading: "Зареждане...", no_data: "Няма данни", no_students: "Няма регистрирани студенти.", chart_label: "Брой студенти",
        
        // Общи за таблиците
        th_id: "ID", th_action: "Управление",
        
        // Студенти
        th_name: "Име", th_fac: "Фак. Номер", th_email: "Имейл", th_courses: "Записани курсове",
        btn_new_student: "Нов Студент", search_student: "Търси по име, фак. номер...",
        
        // Преподаватели
        th_title: "Титла", th_prof_name: "Име и Фамилия", 
        btn_new_prof: "Нов Преподавател", search_prof: "Търси преподавател...",
        
        // Курсове / Специалности
        th_course_name: "Име на курса", th_course: "Специалности", th_credits: "Кредити", th_professor: "Преподавател", th_prof: "Преподавател", th_grade: "Оценка",
        btn_new_course: "Нов Курс", search_course: "Търси курс по име...",
        btn_assign_prof: "Преподавател"
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
        
        th_name: "Name", th_fac: "Faculty No.", th_email: "Email", th_courses: "Enrolled Courses",
        btn_new_student: "New Student", search_student: "Search by name, ID...",
        
        th_title: "Title", th_prof_name: "Full Name", 
        btn_new_prof: "New Professor", search_prof: "Search professor...",
        
        th_course_name: "Course Name", th_course: "Course", th_credits: "Credits", th_professor: "Professor", th_prof: "Professor", th_grade: "Grade",
        btn_new_course: "New Course", search_course: "Search course by name...",
        btn_assign_prof: "Professor"
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
        
        th_name: "Nom", th_fac: "Numéro d'étud.", th_email: "E-mail", th_courses: "Cours inscrits",
        btn_new_student: "Nouvel Étudiant", search_student: "Rechercher un étudiant...",
        
        th_title: "Titre", th_prof_name: "Nom et Prénom", 
        btn_new_prof: "Nouveau Professeur", search_prof: "Rechercher un professeur...",
        
        th_course_name: "Nom du cours", th_course: "Cours", th_credits: "Crédits", th_professor: "Professeur", th_prof: "Professeur", th_grade: "Note",
        btn_new_course: "Nouveau Cours", search_course: "Rechercher un cours...",
        btn_assign_prof: "Professeur"
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
        
        th_name: "Nombre", th_fac: "Nº Matrícula", th_email: "Correo", th_courses: "Cursos Inscritos",
        btn_new_student: "Nuevo Estudiante", search_student: "Buscar por nombre, ID...",
        
        th_title: "Título", th_prof_name: "Nombre y Apellido", 
        btn_new_prof: "Nuevo Profesor", search_prof: "Buscar profesor...",
        
        th_course_name: "Nombre del Curso", th_course: "Curso", th_credits: "Créditos", th_professor: "Profesor", th_prof: "Profesor", th_grade: "Nota",
        btn_new_course: "Nuevo Curso", search_course: "Buscar curso...",
        btn_assign_prof: "Profesor"
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
        
        th_name: "Name", th_fac: "Matrikelnummer", th_email: "E-Mail", th_courses: "Eingeschriebene Kurse",
        btn_new_student: "Neuer Student", search_student: "Suchen nach Name, ID...",
        
        th_title: "Titel", th_prof_name: "Vor- und Nachname", 
        btn_new_prof: "Neuer Professor", search_prof: "Professor suchen...",
        
        th_course_name: "Kursname", th_course: "Kurs", th_credits: "Credits", th_professor: "Professor", th_prof: "Professor", th_grade: "Note",
        btn_new_course: "Neuer Kurs", search_course: "Kurs suchen...",
        btn_assign_prof: "Professor"
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
        
        th_name: "Nome", th_fac: "Matricola", th_email: "Email", th_courses: "Corsi iscritti",
        btn_new_student: "Nuovo Studente", search_student: "Cerca studente...",
        
        th_title: "Titolo", th_prof_name: "Nome e Cognome", 
        btn_new_prof: "Nuovo Professore", search_prof: "Cerca professore...",
        
        th_course_name: "Nome del corso", th_course: "Corso", th_credits: "Crediti", th_professor: "Professore", th_prof: "Professore", th_grade: "Voto",
        btn_new_course: "Nuovo Corso", search_course: "Cerca corso...",
        btn_assign_prof: "Professore"
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
        
        th_name: "Имя", th_fac: "Номер зачетки", th_email: "Email", th_courses: "Записанные курсы",
        btn_new_student: "Новый Студент", search_student: "Поиск студента...",
        
        th_title: "Должность", th_prof_name: "Имя и Фамилия", 
        btn_new_prof: "Новый Преподаватель", search_prof: "Поиск преподавателя...",
        
        th_course_name: "Название курса", th_course: "Специальность", th_credits: "Кредиты", th_professor: "Преподаватель", th_prof: "Преподаватель", th_grade: "Оценка",
        btn_new_course: "Новый Курс", search_course: "Поиск курса...",
        btn_assign_prof: "Преподаватель"
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

    // Рестартираме само Главното табло, за да се преначертае Chart.js графиката на новия език
    if(window.location.pathname.endsWith('index.html') || window.location.pathname === '/') {
        location.reload(); 
    } else {
        // Ако сме на друга страница (Студенти/Курсове), презареждаме таблицата, за да се преведат бутоните вътре
        if(typeof window.loadAllStudents === 'function') window.loadAllStudents();
        if(typeof window.loadAllProfessors === 'function') window.loadAllProfessors();
        if(typeof window.loadAllCourses === 'function') window.loadAllCourses();
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