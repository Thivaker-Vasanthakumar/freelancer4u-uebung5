import 'dotenv/config';
import { error } from '@sveltejs/kit';

const API_BASE_URL = process.env.API_BASE_URL;

export async function load({ fetch, locals }) {
	const jwt_token = locals.jwt_token;

	if (!jwt_token) {
		return {
			companies: []
		};
	}

	const response = await fetch(`${API_BASE_URL}/api/company`, {
		headers: {
			Authorization: 'Bearer ' + jwt_token
		}
	});

	let companies = [];
	if (response.ok) {
		companies = await response.json();
	}

	return {
		companies
	};
}

export const actions = {
	default: async ({ request, fetch, locals }) => {
		const jwt_token = locals.jwt_token;

		if (!jwt_token) {
			throw error(401, 'Authentication required');
		}

		const formData = await request.formData();

		const name = formData.get('name');
		const email = formData.get('email');

		const response = await fetch(`${API_BASE_URL}/api/company`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				Authorization: 'Bearer ' + jwt_token
			},
			body: JSON.stringify({
				name,
				email
			})
		});

		if (!response.ok) {
			return {
				success: false,
				message: 'Company konnte nicht erstellt werden.'
			};
		}

		return {
			success: true,
			message: 'Company erfolgreich erstellt.'
		};
	}
};