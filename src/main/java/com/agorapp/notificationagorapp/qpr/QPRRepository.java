package com.agorapp.notificationagorapp.qpr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QPRRepository extends JpaRepository<QPR, String> {

	@Query(value = """
			SELECT radicado, nombre, pqrs, canal, username, secretaria, fecha_utc
			FROM pqrs
			WHERE regexp_replace(lower(trim(translate(secretaria,
				'ÁÉÍÓÚÀÈÌÒÙÄËÏÖÜáéíóúàèìòùäëïöüÑñ',
				'AEIOUAEIOUAEIOUaeiouaeiouaeiounn'))), '\\s+', ' ', 'g') = :secretariaNormalizada
			""", nativeQuery = true)
	List<QPR> findBySecretariaNormalizada(@Param("secretariaNormalizada") String secretariaNormalizada);
}
