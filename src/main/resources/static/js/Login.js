(function() {
    // Obtener elementos para validación básica y feedback profesional
    const form = document.getElementById('loginForm');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');

    // Simular registro (interacción amigable manteniendo profesionalismo)
    const registerLink = document.getElementById('registerMock');
    if (registerLink) {
        registerLink.addEventListener('click', (e) => {
            e.preventDefault();
            // Notificación moderna y sutil (sin alert intrusivo)
            showFloatingMessage('✨ Próximamente: abre tu cuenta en Servi-Tareas', '#7C3AED');
        });
    }

    // Función para mensajes flotantes estilizados (no invasivos)
    function showFloatingMessage(msg, color = '#3B82F6') {
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

    if (form) {
        form.addEventListener('submit', (e) => {
            e.preventDefault();
            const email = emailInput.value.trim();
            const password = passwordInput.value.trim();

            if (!email) {
                showFloatingMessage('📧 Ingresa un correo electrónico válido', '#F97316');
                emailInput.focus();
                return;
            }
            if (!email.includes('@') || !email.includes('.')) {
                showFloatingMessage('⚠️ Formato de correo incorrecto', '#F97316');
                emailInput.focus();
                return;
            }
            if (!password) {
                showFloatingMessage('🔒 Escribe tu contraseña para continuar', '#F97316');
                passwordInput.focus();
                return;
            }
            if (password.length < 6) {
                showFloatingMessage('🔒 La contraseña debe tener al menos 6 caracteres', '#F97316');
                passwordInput.focus();
                return;
            }

            setTimeout(() => {
                alert('Demostración interactiva • Has iniciado sesión correctamente.\nBienvenido al ecosistema Servi-Tareas.');
            }, 800);
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

    const bearElement = document.querySelector('.bear-sleeping');
    if (bearElement) {
        bearElement.addEventListener('mouseenter', () => {
            // Acción sutil opcional, no interrumpe animación.
        });
    }

    const style = document.createElement('style');
    style.textContent = `
            .bear-sleeping {
                will-change: transform;
            }
            .droplet {
                will-change: opacity, transform;
            }
            .custom-toast-message {
                transition: opacity 0.25s ease;
                pointer-events: none;
                font-weight: 500;
                background: #1A1C28;
                border: 1px solid rgba(255,255,255,0.08);
            }
            .input-group.focused-effect label {
                color: #A78BFA;
            }
        `;
    document.head.appendChild(style);

    const leftArm = document.querySelector('.arm.left');
    const rightArm = document.querySelector('.arm.right');
    if (leftArm && rightArm) {
        // Ajuste dinámico sencillo para una sensación de abrazo.
    }

    const loginBtn = document.querySelector('.btn-login');
    if (loginBtn) {
        loginBtn.addEventListener('click', () => {
            loginBtn.style.transform = 'scale(0.97)';
            setTimeout(() => {
                if (loginBtn) loginBtn.style.transform = '';
            }, 150);
        });
    }

    console.log('Interfaz Servi-Tareas | Login moderna con oso polar animado');
})();
