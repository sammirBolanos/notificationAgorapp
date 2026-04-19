package com.agorapp.notificationagorapp.qpr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QPRRepository extends JpaRepository<QPR, String> {

	@Modifying
	@Query(value = """
			UPDATE pqrs_procesada
			SET secretaria = :secretaria
			WHERE radicado = :radicado
			""", nativeQuery = true)
	int actualizarSecretariaPorRadicado(@Param("radicado") String radicado, @Param("secretaria") String secretaria);

	@Query(value = """
			SELECT radicado, pqrs, canal, fecha_utc, username, nombre, secretaria,
			       titulo_ia, resumen_ia, clasificacion, fecha_limite, irrespetuosa, resuelta
			FROM pqrs_procesada
			WHERE regexp_replace(
				lower(trim(translate(secretaria,
					'ÁÉÍÓÚÀÈÌÒÙÄËÏÖÜáéíóúàèìòùäëïöüÑñ',
					'AEIOUAEIOUAEIOUaeiouaeiouaeiounn'))),
				'\\s+',
				' ',
				'g'
			) = :secretariaNormalizada
			""", nativeQuery = true)
	List<QPR> findBySecretariaNormalizada(@Param("secretariaNormalizada") String secretariaNormalizada);
}
