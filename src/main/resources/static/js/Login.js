(function() {
    const cardWrapper = document.getElementById('cardWrapper');
    const loginFormContainer = document.getElementById('loginFormContainer');
    const registerFormContainer = document.getElementById('registerFormContainer');
    const showRegisterBtn = document.getElementById('showRegisterBtn');
    const showLoginBtn = document.getElementById('showLoginBtn');
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');

    const loginEmail = document.getElementById('loginEmail');
    const loginPassword = document.getElementById('loginPassword');
    const regName = document.getElementById('regName');
    const regEmail = document.getElementById('regEmail');
    const regPhone = document.getElementById('regPhone');
    const regPassword = document.getElementById('regPassword');
    const regApellido = document.getElementById('regApellido');

    function showMessage(msg, color = '#3B82F6') {
        const existing = document.querySelector('.custom-toast-message');
        if (existing) existing.remove();

        const toast = document.createElement('div');
        toast.className = 'custom-toast-message';
        toast.innerText = msg;
        toast.style.position = 'fixed';
        toast.style.bottom = '30px';
        toast.style.left = '50%';
        toast.style.transform = 'translateX(-50%)';
        toast.style.backgroundColor = '#1E1F2C';
        toast.style.backdropFilter = 'blur(8px)';
        toast.style.borderLeft = `4px solid ${color}`;
        toast.style.color = '#F0F3FA';
        toast.style.padding = '12px 24px';
        toast.style.borderRadius = '40px';
        toast.style.fontFamily = "'Inter', sans-serif";
        toast.style.fontSize = '0.85rem';
        toast.style.fontWeight = '500';
        toast.style.boxShadow = '0 12px 20px rgba(0,0,0,0.4)';
        toast.style.zIndex = '1000';
        toast.style.letterSpacing = '0.2px';
        toast.style.whiteSpace = 'nowrap';
        toast.style.opacity = '1';
        document.body.appendChild(toast);

        setTimeout(() => {
            toast.style.opacity = '0';
            toast.style.transition = 'opacity 0.3s';
            setTimeout(() => toast.remove(), 400);
        }, 2800);
    }

    function enableRegisterMode() {
        cardWrapper.classList.add('register-mode');
        loginFormContainer.classList.add('hide');
        loginFormContainer.classList.remove('active');
        registerFormContainer.classList.add('active');
        registerFormContainer.style.display = 'block';
    }

    function enableLoginMode() {
        cardWrapper.classList.remove('register-mode');
        loginFormContainer.classList.remove('hide');
        loginFormContainer.classList.add('active');
        registerFormContainer.classList.remove('active');
        registerFormContainer.style.display = 'none';
    }

    if (showRegisterBtn) {
        showRegisterBtn.addEventListener('click', (e) => {
            e.preventDefault();
            enableRegisterMode();
        });
    }

    if (showLoginBtn) {
        showLoginBtn.addEventListener('click', (e) => {
            e.preventDefault();
            enableLoginMode();
        });
    }

    if (loginForm) {
        loginForm.addEventListener('submit', (e) => {
            const email = loginEmail.value.trim();
            const pass = loginPassword.value.trim();

            if (!email) {
                e.preventDefault();
                showMessage('📧 Ingresa tu correo electrónico', '#F97316');
                loginEmail.focus();
                return;
            }
            if (!email.includes('@') || !email.includes('.')) {
                e.preventDefault();
                showMessage('⚠️ Formato de correo incorrecto', '#F97316');
                loginEmail.focus();
                return;
            }
            if (!pass) {
                e.preventDefault();
                showMessage('🔒 Escribe tu contraseña', '#F97316');
                loginPassword.focus();
                return;
            }
            showMessage('✅ Iniciando sesión...', '#10B981');
        });
    }

    if (registerForm) {
        registerForm.addEventListener('submit', (e) => {
            const name = regName.value.trim();
            const email = regEmail.value.trim();
            const pwd = regPassword.value.trim();

            if (!name) {
                e.preventDefault();
                showMessage('👤 Ingresa tu nombre completo', '#F97316');
                regName.focus();
                return;
            }
            if (!email || !email.includes('@') || !email.includes('.')) {
                e.preventDefault();
                showMessage('📧 Ingresa un correo electrónico válido', '#F97316');
                regEmail.focus();
                return;
            }
            if (!pwd || pwd.length < 4) {
                e.preventDefault();
                showMessage('🔒 La contraseña debe tener al menos 4 caracteres', '#F97316');
                regPassword.focus();
                return;
            }

            const parts = name.split(' ').filter(Boolean);
            regApellido.value = parts.length > 1 ? parts.slice(1).join(' ') : '';
            showMessage('🎉 Registro listo, enviando...', '#A78BFA');
        });
    }

    const inputs = document.querySelectorAll('.input-field');
    inputs.forEach(input => {
        input.addEventListener('focus', () => {
            input.parentElement.classList.add('focused-effect');
        });
        input.addEventListener('blur', () => {
            input.parentElement.classList.remove('focused-effect');
        });
    });

    const style = document.createElement('style');
    style.textContent = `
            .bear-sleeping { will-change: transform; }
            .droplet { will-change: opacity, transform; }
            .custom-toast-message { transition: opacity 0.25s ease; pointer-events: none; font-weight: 500; background: #1A1C28; border: 1px solid rgba(255,255,255,0.08); }
            .input-group.focused-effect label { color: #A78BFA; }
        `;
    document.head.appendChild(style);

    const loginBtn = document.querySelector('.btn-login');
    if (loginBtn) {
        loginBtn.addEventListener('click', () => {
            loginBtn.style.transform = 'scale(0.97)';
            setTimeout(() => { if (loginBtn) loginBtn.style.transform = ''; }, 150);
        });
    }

    const registerBtn = document.querySelector('.btn-register');
    if (registerBtn) {
        registerBtn.addEventListener('click', () => {
            registerBtn.style.transform = 'scale(0.97)';
            setTimeout(() => { if (registerBtn) registerBtn.style.transform = ''; }, 150);
        });
    }

    enableLoginMode();
})();
